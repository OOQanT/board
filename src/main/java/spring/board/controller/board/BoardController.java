package spring.board.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.board.domain.Member;
import spring.board.dto.board.BoardDto;
import spring.board.dto.board.SearchContentDto;
import spring.board.repository.MemberRepository;
import spring.board.service.BoardService;
import spring.board.service.MemberService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String board(Model model){
        model.addAttribute("form", new BoardDto());
        return "board/board";
    }

    @PostMapping("/createContent")
    public String createContent(@Validated @ModelAttribute("form") BoardDto boardDto,
                                BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "board/board";
        }

        boardService.save(boardDto,getAuthMember().getUsername());

        return "redirect:/";
    }

    @GetMapping("/contents")
    public String contents(Model model){
        List<SearchContentDto> contents = boardService.findContent();
        model.addAttribute("contents",contents);
        return "board/contents";
    }

    @GetMapping("/contents/{contentId}")
    public String contentDetail(@PathVariable("contentId") Long contentId, Model model){
        BoardDto findContent = boardService.findById(contentId);
        model.addAttribute("form",findContent);
        model.addAttribute("contentId",contentId);

        // 인증된 사용자의 id 값을 넘겨줌 contentId 값과 다르면 수정 버튼 비활성
        model.addAttribute("userId",getAuthMember().getId());

        return "board/content";
    }

    private static Member getAuthMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        return member;
    }

    /*@GetMapping("/content/{contentId}/edit")
    public String editContent(@PathVariable("contentId") Long contentId, Model model){

    }*/


}
