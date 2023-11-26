package spring.board.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.board.domain.Member;
import spring.board.dto.member.MemberDto;
import spring.board.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("form",new MemberDto());
        return "member/addForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("form") MemberDto memberDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "member/addForm";
        }

        if(!memberDto.getPassword().equals(memberDto.getRePassword())){
            bindingResult.reject("Inconsistency_RePassword","다시 입력한 비밀번호가 일치하지 않습니다");
            return "member/addForm";
        }

        memberService.save(memberDto);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model,
                            @RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception", required = false)String exception
    ){
        model.addAttribute("form",new MemberDto());
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "member/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception",required = false) String exception,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();
        model.addAttribute("username",member.getUsername());
        model.addAttribute("exception",exception);
        return "member/denied";
    }
}
