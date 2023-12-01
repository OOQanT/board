package spring.board.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String basicInternalServerError(Model model, Exception ex){
        log.info("ex.message ={}",ex.getMessage());

        model.addAttribute("message","잘못된 접근입니다.");
        model.addAttribute("searchUrl","/");

        return "warningPage";
    }

}
