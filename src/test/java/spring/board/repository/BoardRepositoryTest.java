package spring.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Board;
import spring.board.domain.Member;
import spring.board.dto.board.SearchContentDto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void pageTest(){

        PageRequest pageRequest = PageRequest.of(0,2);

        Page<SearchContentDto> result = boardRepository.searchContentPage(pageRequest);

        System.out.println("totalPages = " + result.getTotalPages()); //전체 페이지 수
        System.out.println("getSize() = " + result.getSize()); //현재 페이지의 크기
        System.out.println("result.getTotalElements() = " + result.getTotalElements()); //전체 콘텐츠 개수
        System.out.println("result.getNumberOfElements() = " + result.getNumberOfElements()); // 현재 페이지에 포함된 실제 엔티티(데이터) 개수
        System.out.println("result.getPageable().getOffset() = " + result.getPageable().getOffset()); // offset
        System.out.println("result.previousPageable().getPageNumber() = " + result.previousPageable().getPageNumber()); // 이전 페이지의 넘버를 얻어옴
        System.out.println("result.nextPageable().getPageNumber() = " + result.nextPageable().getPageNumber()); //다음 페이지의 번호를 얻음


        System.out.println("result.getNumber() = " + result.getNumber()); // 현재 페이지의 번호
        System.out.println("result.isFirst() = " + result.isFirst()); //현재 페이지가 처음인지 판단
        System.out.println("result.isLast() = " + result.isLast()); //현재 페이지가 마지막인지 판단
        System.out.println("result.hasNext() = " + result.hasNext()); // 다음 페이지가 있는지 판단
        System.out.println("result.hasPrevious() = " + result.hasPrevious()); // 이전 페이지가 있는지 판단




        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getContent()).extracting("author").containsExactly("user","user");
    }


    @Test
    public void findById(){
        Board findBoard = boardRepository.findById(1L).orElse(null);
        Member member = findBoard.getMember();

        assertThat(member.getNickname()).isEqualTo("user");
    }

}