package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.Board;
import spring.board.repository.custom.BoardRepositoryImpl;
import spring.board.repository.custom.CustomBoardRepository;

public interface BoardRepository extends JpaRepository<Board,Long> ,CustomBoardRepository {
}
