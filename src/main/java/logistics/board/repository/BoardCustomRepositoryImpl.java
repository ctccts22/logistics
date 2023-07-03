package logistics.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import logistics.board.dto.BoardDTO;
import logistics.board.entity.Board;
import logistics.board.entity.QBoard;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {

    private static final QBoard qboard = QBoard.board;

    public BoardCustomRepositoryImpl() {
        super(Board.class);
    }

    @Transactional
    @Override
    public void incrementViewCount(Long id) {
        JPAUpdateClause updateClause = new JPAUpdateClause(getEntityManager(), qboard);
        updateClause
                .where(qboard.id.eq(id))
                .set(qboard.view, qboard.view.add(1))
                .execute();
    }

    @Override
    public List<BoardDTO> searchBoards(String searchQuery, String searchType) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
        BooleanBuilder predicate = new BooleanBuilder();

        if ("title".equals(searchType)) {
            predicate.and(qboard.title.containsIgnoreCase(searchQuery));
        } else if ("writer".equals(searchType)) {
            predicate.and(qboard.writer.containsIgnoreCase(searchQuery));
        }

        List<Board> boards = queryFactory.selectFrom(qboard)
                .where(predicate)
                .fetch();

        return boards.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BoardDTO mapToDTO(Board board) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(board, BoardDTO.class);
    }
}
