package spring.board.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDetailDto {

    private String nickname;
    private String title;
    private String content;

    public ContentDetailDto(String nickname, String title, String content) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
    }
}
