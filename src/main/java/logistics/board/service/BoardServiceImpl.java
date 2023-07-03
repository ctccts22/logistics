package logistics.board.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.board.dto.BoardDTO;
import logistics.board.entity.Board;
import logistics.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addBoard(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);
    }

    @Override
    public Page<BoardDTO> getBoardPage(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        return boardPage.map(board -> modelMapper.map(board, BoardDTO.class));
    }

    @Override
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디를 찾을 수 없습니다 "));

        boardRepository.delete(board);
    }

    @Override
    public void updateBoard(Long id, BoardDTO boardDTO) {
        Optional<Board> boardOptional = boardRepository.findById(id);

        if(boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.updateWith(boardDTO);
            boardRepository.save(board);
        } else {
            throw new EntityNotFoundException("아이디를 찾을 수 없습니다 " + id);
        }
    }

    @Override
    public List<BoardDTO> searchBoards(String searchQuery, String searchType) {
        return boardRepository.searchBoards(searchQuery, searchType);
    }

}
