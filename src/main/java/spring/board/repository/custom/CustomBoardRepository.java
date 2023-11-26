package spring.board.repository.custom;

import spring.board.dto.board.SearchContentDto;

import java.util.List;

public interface CustomBoardRepository {
    List<SearchContentDto> searchContent();
}
