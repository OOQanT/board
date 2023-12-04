package spring.board.dto.boardcomment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCommentDto {

    private String comment;

    public EditCommentDto(String comment) {
        this.comment = comment;
    }
}
