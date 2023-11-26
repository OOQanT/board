package spring.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Board;
import spring.board.domain.Member;
import spring.board.dto.board.BoardDto;
import spring.board.dto.board.SearchContentDto;
import spring.board.repository.BoardRepository;
import spring.board.repository.MemberRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void save(BoardDto boardDto, String username){
        Member findMember = memberRepository.findByUsername(username);
        Board board = new Board(boardDto);
        board.setMember(findMember);
        boardRepository.save(board);
    }

    public List<SearchContentDto> findContent(){
        return boardRepository.searchContent();
    }
}
