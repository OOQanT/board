package spring.board.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import spring.board.domain.QBoard;
import spring.board.domain.QMember;
import spring.board.dto.board.QSearchContentDto;
import spring.board.dto.board.SearchCondition;
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
                .innerJoin(board.member, member)
                .fetch();


        return searchContents;
    }

    @Override
    public Page<SearchContentDto> searchContentPage(Pageable pageable) {
        List<SearchContentDto> results = query
                .select(new QSearchContentDto(
                        board.id.as("contentId"),
                        board.title,
                        board.member.nickname.as("author"),
                        board.createTime
                ))
                .from(board)
                .innerJoin(board.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query
                .select(board.count())
                .from(board)
                .fetchOne();


        return new PageImpl<>(results,pageable,count);
    }

    @Override
    public Page<SearchContentDto> searchContentPage(SearchCondition searchCondition, Pageable pageable) {

        List<SearchContentDto> results = query
                .select(new QSearchContentDto(
                        board.id.as("contentId"),
                        board.title,
                        board.member.nickname.as("author"),
                        board.createTime
                ))
                .from(board)
                .innerJoin(board.member, member)
                .where(
                        nicknameEq(searchCondition.getNickname()),
                        titleLike(searchCondition.getTitle())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query
                .select(board.count())
                .from(board)
                .where(
                        nicknameEq(searchCondition.getNickname()),
                        titleLike(searchCondition.getTitle())
                )
                .fetchOne();

        return new PageImpl<>(results,pageable,count);
    }

    private BooleanExpression nicknameEq(String nickname){
        return StringUtils.hasText(nickname) ? board.member.nickname.eq(nickname) : null;
    }

   private BooleanExpression titleLike(String title){
        return StringUtils.hasText(title) ? board.title.like("%" + title + "%") : null;
   }
}
