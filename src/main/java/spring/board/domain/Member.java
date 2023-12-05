package spring.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import spring.board.dto.member.MemberDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createTime;

    public Member(MemberDto memberDto) {
        this.username = memberDto.getUsername();
        this.password = memberDto.getPassword();
        this.nickname = memberDto.getNickname();
        this.email = memberDto.getEmail();
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRoleUser(){
        this.role = "ROLE_USER";
    }

    public void setRoleManager(){
        this.role = "ROLE_MANAGER";
    }
}
