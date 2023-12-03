package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.board.domain.BoardComment;
import spring.board.dto.boardcomment.FindCommentDto;
import spring.board.repository.custom.CustomBoardCommentRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment,Long> , CustomBoardCommentRepository {
}
