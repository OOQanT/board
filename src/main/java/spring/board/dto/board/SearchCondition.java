package spring.board.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SearchCondition {

    private String nickname;
    private String title;

    public SearchCondition(String nickname, String title) {
        this.nickname = nickname;
        this.title = title;
    }
    public SearchCondition() {}

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
