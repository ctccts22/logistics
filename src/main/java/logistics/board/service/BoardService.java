package logistics.board.service;

import logistics.board.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    void addBoard(BoardDTO boardDTO);
    Page<BoardDTO> getBoardPage(Pageable pageable);
    void deleteBoard(Long id);
    void updateBoard(Long id, BoardDTO boardDTO);
    List<BoardDTO> searchBoards(String searchQuery, String searchType);
}
