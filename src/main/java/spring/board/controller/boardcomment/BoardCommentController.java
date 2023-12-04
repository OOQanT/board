package spring.board.controller.boardcomment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import spring.board.domain.Member;
import spring.board.dto.boardcomment.PostAuthRequest;
import spring.board.dto.boardcomment.SaveCommentDto;
import spring.board.service.BoardCommentService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;


    //contentId 로 board객체를 찾아야함 찾아서 board.member.nickname을 찾고
    //SaveCommentDto, board, board.member.nickname
    @PostMapping("/contents/{contentId}")
    public String saveComment(@PathVariable Long contentId, @RequestBody SaveCommentDto saveCommentDto, Model model){

        if(!StringUtils.hasText(saveCommentDto.getComment())){
            model.addAttribute("message","댓글을 입력해주세요.");
            model.addAttribute("searchUrl","/contents/"+contentId);
            return "warningPage";
        }

        boardCommentService.save(contentId,saveCommentDto);
        return "redirect:/contents/" + contentId;
    }

    @GetMapping("/comment/{commentId}/delete") // 댓글의 PK값으로 댓글을 찾아서 삭제
    public String deleteComment(@PathVariable Long commentId, Model model){

        PostAuthRequest userInfoForComment = boardCommentService.getUserInfoForComment(commentId);
        String username = userInfoForComment.getUsername();

        log.info("commentUsername={}",userInfoForComment.getUsername());
        log.info("authUsername={}",getAuthMember().getUsername());

        if(!username.equals(getAuthMember().getUsername())){
            model.addAttribute("message","다른 사용자의 댓글입니다.");
            model.addAttribute("searchUrl","/contents/" + userInfoForComment.getBoardId());
            return "warningPage";
        }

        // 삭제하고 그 게시물의 번호를 리턴
        boardCommentService.deleteComment(commentId);

        return "redirect:/contents/" + userInfoForComment.getBoardId();
    }

    private static Member getAuthMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        return member;
    }
}
