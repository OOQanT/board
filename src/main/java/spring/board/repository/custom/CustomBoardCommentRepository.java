package spring.board.repository.custom;

import spring.board.dto.boardcomment.FindCommentDto;

import java.util.List;

public interface CustomBoardCommentRepository {
    List<FindCommentDto> findByContentId(Long contentId);
    FindCommentDto findByCommentId(Long commentId);
}
