package spring.board;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.board.dto.board.BoardDto;
import spring.board.dto.member.MemberDto;
import spring.board.service.BoardService;
import spring.board.service.MemberService;

@SpringBootApplication
@RequiredArgsConstructor
public class BoardApplication {

	private final MemberService memberService;
	private final BoardService boardService;

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	@PostConstruct
	public void init(){
		MemberDto user = new MemberDto("user", "1111", "1111", "user", "onjs@naver.com");
		MemberDto manager = new MemberDto("manager", "1111", "1111", "manager", "onjs@gmail.com");
		memberService.save(user);
		memberService.save(manager);

		for(int i=0; i<50; i++){
			boardService.save(new BoardDto("test"+i,"test"+i),user.getUsername());
		}

		boardService.save(new BoardDto("안녕하세요1","안녕하세요"),manager.getUsername());
		boardService.save(new BoardDto("안녕하세요2","안녕하세요"),manager.getUsername());
		boardService.save(new BoardDto("안녕하세요3","안녕하세요"),manager.getUsername());
		boardService.save(new BoardDto("안녕하세요4","안녕하세요"),manager.getUsername());
		boardService.save(new BoardDto("반가워요1","반가워요"), manager.getUsername());
		boardService.save(new BoardDto("반가워요2","반가워요"), manager.getUsername());
		boardService.save(new BoardDto("반가워요3","반가워요"), manager.getUsername());
		boardService.save(new BoardDto("반가워요4","반가워요"), manager.getUsername());

	}
}
