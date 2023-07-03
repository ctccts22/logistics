package logistics.board.controller;

import logistics.board.dto.BoardDTO;
import logistics.board.entity.Board;
import logistics.board.repository.BoardRepository;
import logistics.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    /**
     * 게시글 조회
     * @param model
     * @param pageable 페이징 적용
     * @return
     */
//    @GetMapping("/boardView")
//    public String showBoardViewForm(Model model,
//                                    @PageableDefault(page=0, size=10, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
//        Page<BoardDTO> boardDTOPage = boardService.getBoardPage(pageable);
//
//        int nowPage = boardDTOPage.getPageable().getPageNumber() + 1;
//        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
//        int startPage =  Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage+9, boardDTOPage.getTotalPages());
//
//        model.addAttribute("nowPage",nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        model.addAttribute("title", "게시판");
//        model.addAttribute("boards", boardDTOPage);
//
//        return "board/board_view";
//    }


    @GetMapping("/boardView")
    public String showBoardViewForm(Model model,
                                    @RequestParam(name = "searchQuery", required = false) String searchQuery,
                                    @RequestParam(name = "searchType", required = false) String searchType,
                                    @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardDTO> boardDTOPage = null;

        if (searchQuery != null && searchType != null) {
            List<BoardDTO> boardDTOs = boardService.searchBoards(searchQuery, searchType);
            boardDTOPage = new PageImpl<>(boardDTOs, pageable, boardDTOs.size());
        } else {
            boardDTOPage = boardService.getBoardPage(pageable);
        }

        model.addAttribute("boards", boardDTOPage);

        int nowPage = boardDTOPage.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, boardDTOPage.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("title", "게시판");

        return "board/board_view";
    }


    /**
     * 게시글 상세조회
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model) {
        Optional<Board> boardOptional = boardRepository.findById(id);

        if(boardOptional.isPresent()) {
            Board board = boardOptional.get();
            boardRepository.incrementViewCount(id);

            model.addAttribute("board", board);
            return "board/detail_view";
        } else {
            return "redirect:/board/boardView";
        }
    }

    /**
     * 게시글 등록
     * @param model
     * @return
     */
    @GetMapping("/addBoard")
    public String addBoardView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setWriter(username);

        model.addAttribute("board", boardDTO);
        return "board/add_board";
    }
    @PostMapping("/addBoard")
    public String addBoard(@ModelAttribute BoardDTO boardDTO) {
        boardService.addBoard(boardDTO);
        return "redirect:/board/boardView";
    }

    /**
     * 삭제 기능
     * @param id
     * @param redirectAttributes javascript alert 연결
     * @return
     */
    @RequestMapping("/{id}/deleteBoard")
    public String deleteBoard(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Board board = boardRepository.findById(id).orElse(null);

        if (board == null) {
            redirectAttributes.addFlashAttribute("error", "게시글을 찾을 수 없습니다.");
            return "redirect:/board/boardView";
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean authorized = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (!authorized && !board.getWriter().equals(currentPrincipalName)) {
            redirectAttributes.addFlashAttribute("error", "삭제 권한이 없습니다.");
            return "redirect:/board/boardView";
        }

        boardService.deleteBoard(id);

        redirectAttributes.addFlashAttribute("deletionSuccess", true);
        return "redirect:/board/boardView";
    }

    /**
     * 게시글 수정
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}/updateBoard")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 아이디값:" + id));
        model.addAttribute("board", board);
        return "board/update_board";
    }
    @PostMapping("/{id}/updateBoard")
    public String updateBoard(@PathVariable Long id, @ModelAttribute BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        Authentication authenticationForUpdate = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authenticationForUpdate.getName();

        Board board = boardRepository.findById(id).orElse(null);

        if (board == null) {
            redirectAttributes.addFlashAttribute("error", "게시글을 찾을 수 없습니다.");
            return "redirect:/board/boardView";
        }

        Collection<? extends GrantedAuthority> authorities = authenticationForUpdate.getAuthorities();
        boolean authorized = authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (!authorized && !board.getWriter().equals(currentPrincipalName)) {
            redirectAttributes.addFlashAttribute("error", "수정 권한이 없습니다.");
            return "redirect:/board/boardView";
        }
        boardService.updateBoard(id, boardDTO);

        redirectAttributes.addFlashAttribute("updatedSuccess", true);
        return "redirect:/board/{id}";
    }

}
