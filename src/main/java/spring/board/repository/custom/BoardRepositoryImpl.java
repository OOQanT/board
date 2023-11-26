package spring.board.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import spring.board.domain.QBoard;
import spring.board.domain.QMember;
import spring.board.dto.board.QSearchContentDto;
import spring.board.dto.board.SearchContentDto;

import java.util.List;

import static spring.board.domain.QBoard.*;
import static spring.board.domain.QMember.*;


@RequiredArgsConstructor
public class BoardRepositoryImpl implements CustomBoardRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    @Override
    public List<SearchContentDto> searchContent() {

        List<SearchContentDto> searchContents = query
                .select(new QSearchContentDto(
                        board.id.as("contentId"),
                        board.title,
                        board.member.nickname.as("author"),
                        board.createTime
                ))
                .from(board)
                .join(board.member, member).fetchJoin()
                .fetch();

        return searchContents;
    }
}
