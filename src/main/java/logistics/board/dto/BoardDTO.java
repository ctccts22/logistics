package logistics.board.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Integer view = 0;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime regDate;
}
