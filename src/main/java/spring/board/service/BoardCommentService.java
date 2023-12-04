package spring.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.board.domain.Board;
import spring.board.domain.BoardComment;
import spring.board.dto.boardcomment.FindCommentDto;
import spring.board.dto.boardcomment.PostAuthRequest;
import spring.board.dto.boardcomment.SaveCommentDto;
import spring.board.repository.BoardCommentRepository;
import spring.board.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;

    public void save(Long contentId, SaveCommentDto saveCommentDto) {
        Board findBoard = boardRepository.findById(contentId).orElseThrow();
        String nickname = findBoard.getMember().getNickname();

        BoardComment boardComment = new BoardComment(saveCommentDto.getComment(), nickname);
        boardComment.setBoard(findBoard);
        boardCommentRepository.save(boardComment);
    }

    public List<FindCommentDto> findComments(Long contentId) {
        List<FindCommentDto> findContents = boardCommentRepository.findByContentId(contentId);
        return findContents;
    }

    public void deleteComment(Long commentId) {
        BoardComment findComment = boardCommentRepository.findById(commentId).orElseThrow();
        boardCommentRepository.delete(findComment);
    }

    public PostAuthRequest getUserInfoForComment(Long commentId){
        PostAuthRequest findAuth = boardCommentRepository.findAuthByCommentId(commentId);
        return findAuth;
    }
}
