package spring.board.dto.boardcomment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCommentDto {

    private String comment;

    public SaveCommentDto(String comment) {
        this.comment = comment;
    }

    public SaveCommentDto() {
    }
}
