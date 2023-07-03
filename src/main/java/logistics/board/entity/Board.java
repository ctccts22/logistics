package logistics.board.entity;

import jakarta.persistence.*;
import logistics.board.dto.BoardDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Long id;

    @Column(name = "board_title", length = 30, nullable = false)
    private String title;

    @Column(name = "board_content", length = 255, nullable = false)
    private String content;

    @Column(name = "board_writer", length = 255, nullable = false)
    private String writer;

    @Column(name = "board_view")
    private Integer view = 0;

    @Column(name = "board_regdate")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Builder(toBuilder = true)
    public Board(Long id, String title, String content, String writer, Integer view, LocalDateTime regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = view == null ? 0 : view;
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void updateWith(BoardDTO boardDTO) {
        if (boardDTO.getTitle() != null) {
            this.title = boardDTO.getTitle();
        }

        if (boardDTO.getContent() != null) {
            this.content = boardDTO.getContent();
        }
    }
}
