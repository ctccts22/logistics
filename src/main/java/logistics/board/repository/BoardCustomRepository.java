package logistics.board.repository;

import logistics.board.dto.BoardDTO;

import java.util.List;

public interface BoardCustomRepository {
    void incrementViewCount(Long id);

    List<BoardDTO> searchBoards(String searchQuery, String searchType);

}
