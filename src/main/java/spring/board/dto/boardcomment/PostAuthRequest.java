package spring.board.dto.boardcomment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAuthRequest {
    private Long commentId; // 댓글의 ID
    private Long boardId;  // 게시글의 ID
    private String username; // 게시글을 등록한 사람의 username

    public PostAuthRequest() {}

    @QueryProjection
    public PostAuthRequest(Long commentId, Long boardId, String username) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.username = username;
    }
}
