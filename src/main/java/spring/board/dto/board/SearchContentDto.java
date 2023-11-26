package spring.board.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchContentDto {

    private Long contentId;
    private String title;
    private String author;
    private LocalDateTime createTime;
    public SearchContentDto() {}

    @QueryProjection
    public SearchContentDto(Long contentId, String title, String author, LocalDateTime createTime) {
        this.contentId = contentId;
        this.title = title;
        this.author = author;
        this.createTime = createTime;
    }

}
