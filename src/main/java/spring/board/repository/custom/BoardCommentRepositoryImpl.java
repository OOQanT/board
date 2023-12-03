package spring.board.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import spring.board.domain.QBoardComment;
import spring.board.dto.boardcomment.FindCommentDto;
import spring.board.dto.boardcomment.QFindCommentDto;


import java.util.List;

import static spring.board.domain.QBoardComment.*;


@RequiredArgsConstructor
public class BoardCommentRepositoryImpl implements CustomBoardCommentRepository{

    private final JPAQueryFactory query;

    @Override
    public List<FindCommentDto> findByContentId(Long contentId) {

        List<FindCommentDto> findCommentDtos = query
                .select(new QFindCommentDto(
                        boardComment.id.as("commentId"),
                        boardComment.board.id.as("boardId"),
                        boardComment.nickname,
                        boardComment.comment,
                        boardComment.updateTime.as("lastUpdateTime")
                ))
                .from(boardComment)
                .where(boardComment.board.id.eq(contentId))
                .fetch();


        return findCommentDtos;
    }
}
