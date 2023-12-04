package spring.board.repository.custom;

import spring.board.dto.boardcomment.FindCommentDto;
import spring.board.dto.boardcomment.PostAuthRequest;

import java.util.List;

public interface CustomBoardCommentRepository {
    List<FindCommentDto> findByContentId(Long contentId);
    PostAuthRequest findAuthByCommentId(Long commentId);
}
