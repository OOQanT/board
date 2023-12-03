package spring.board.dto.boardcomment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FindCommentDto {

    private Long commentId;
    private Long boardId;
    private String nickname;
    private String comment;
    private LocalDateTime lastUpdateTime;
    public FindCommentDto() {
    }

    @QueryProjection
    public FindCommentDto(Long commentId, Long boardId, String nickname, String comment, LocalDateTime lastUpdateTime) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.nickname = nickname;
        this.comment = comment;
        this.lastUpdateTime = lastUpdateTime;
    }
}
