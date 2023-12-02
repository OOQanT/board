package spring.board.controller.boardcomment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.board.dto.boardcomment.SaveCommentDto;
import spring.board.service.BoardCommentService;


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
}
