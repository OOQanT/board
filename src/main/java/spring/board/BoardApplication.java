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

		BoardDto test1 = new BoardDto("test1","test1");
		BoardDto test2 = new BoardDto("test2","test2");
		BoardDto test3 = new BoardDto("test3","test3");

		BoardDto test4 = new BoardDto("test4,","test4");
		BoardDto test5 = new BoardDto("test5,","test5");
		BoardDto test6 = new BoardDto("test6,","test6");

		boardService.save(test1,user.getUsername());
		boardService.save(test2,user.getUsername());
		boardService.save(test3,user.getUsername());
		boardService.save(test4,manager.getUsername());
		boardService.save(test5,manager.getUsername());
		boardService.save(test6,manager.getUsername());

	}
}
