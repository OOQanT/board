package spring.board;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.board.dto.member.MemberDto;
import spring.board.service.MemberService;

@SpringBootApplication
@RequiredArgsConstructor
public class BoardApplication {

	private final MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	@PostConstruct
	public void init(){
		MemberDto user = new MemberDto("user", "1111", "1111", "user", "onjs@naver.com");
		MemberDto manager = new MemberDto("manager", "1111", "1111", "manager", "onjs@gmail.com");
		memberService.save(user);
		memberService.save(manager);
	}
}
