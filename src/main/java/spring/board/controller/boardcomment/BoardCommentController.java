package spring.board.controller.boardcomment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String deleteComment(@PathVariable Long commentId){

         Long boardId = boardCommentService.deleteComment(commentId);
         // 삭제하고 그 게시물의 번호를 리턴

        return "redirect:/contents/" + boardId;
    }
}
