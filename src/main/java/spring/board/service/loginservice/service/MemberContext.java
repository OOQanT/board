package spring.board.service.loginservice.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import spring.board.domain.Member;

import java.util.Collection;

public class MemberContext extends User {
    private final Member member;
    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }
    public Member getMember() {
        return member;
    }
}
