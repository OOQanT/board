package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment,Long> {
}
