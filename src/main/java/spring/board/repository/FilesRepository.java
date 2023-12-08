package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.Files;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files,Long> {

    List<Files> findByBoardId(Long boardId);
}
