package spring.board.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.board.dto.board.SearchCondition;
import spring.board.dto.board.SearchContentDto;

import java.util.List;

public interface CustomBoardRepository {
    List<SearchContentDto> searchContent();
    Page<SearchContentDto> searchContentPage(Pageable pageable);

    Page<SearchContentDto> searchContentPage(SearchCondition searchCondition,Pageable pageable);

}
