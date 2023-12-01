package spring.board.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.board.domain.Board;
import spring.board.domain.Member;
import spring.board.dto.board.BoardDto;
import spring.board.dto.board.SearchCondition;
import spring.board.dto.board.SearchContentDto;
import spring.board.repository.MemberRepository;
import spring.board.service.BoardService;
import spring.board.service.MemberService;

import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

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

        return "redirect:/contents";
    }

    //@GetMapping("/contents")
    public String contents(Model model){
        List<SearchContentDto> contents = boardService.findContent();
        model.addAttribute("contents",contents);
        return "board/contents";
    }

    @GetMapping("/contents/{contentId}")
    public String contentDetail(@PathVariable("contentId") Long contentId, Model model){
        Board findContent = boardService.findById(contentId);
        model.addAttribute("form",findContent);
        model.addAttribute("contentId",contentId);


        String boardUser = boardService.findById(contentId).getMember().getUsername();
        model.addAttribute("userId",namesEq(boardUser));

        return "board/content";
    }

    private static Member getAuthMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        return member;
    }

    private static boolean namesEq (String boardUser){
        String username = getAuthMember().getUsername();
        if(username.equals(boardUser)){
            return true;
        }
        return false;
    }


    @GetMapping("/content/{contentId}/edit")
    public String editContent(@PathVariable Long contentId, Model model){
        Board findBoard = boardService.findById(contentId);

        if(!namesEq(findBoard.getMember().getUsername())){
            model.addAttribute("message","다른 사용자의 글입니다.");
            model.addAttribute("searchUrl","/contents");
            return "warningPage";
        }

        model.addAttribute("form",findBoard);

        return "board/editForm";
    }

    @PostMapping("/content/{contentId}/edit")
    public String edit(@Validated @ModelAttribute("form") BoardDto form, BindingResult bindingResult,
                       @PathVariable Long contentId){

        if(bindingResult.hasErrors()){
            return "board/editForm";
        }

        boardService.edit(form,contentId);

        return "redirect:/contents";
    }

    @GetMapping("/delete/content/{contentId}")
    public String delete(@PathVariable Long contentId,Model model){
        Board findBoard = boardService.findById(contentId);
        if(!namesEq(findBoard.getMember().getUsername())){
            model.addAttribute("message","다른 사용자의 글입니다.");
            model.addAttribute("searchUrl","/contents");
            return "warningPage";
        }

        boardService.delete(contentId);

        model.addAttribute("message","글이 삭제되었습니다.");
        model.addAttribute("searchUrl","/contents");
        return "warningPage";
    }


    @GetMapping("/warning")
    public String errorPage(Model model){
        model.addAttribute("message","다른 사용자의 글입니다.");
        model.addAttribute("searchUrl","/contents");
        return "warningPage";
    }

    @GetMapping("/contents")
    public String contentPaging(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size, Model model,
                                @ModelAttribute("searchCondition") SearchCondition searchCondition){

        PageRequest pageable = PageRequest.of(page,size);
        Page<SearchContentDto> result = boardService.pagingContent(searchCondition,pageable);
        model.addAttribute("contentPage",result);

        return "board/pagingContent";
    }



}
