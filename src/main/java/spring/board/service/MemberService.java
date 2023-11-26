package spring.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Member;
import spring.board.dto.member.MemberDto;
import spring.board.repository.MemberRepository;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(MemberDto memberDto){
        Member member = new Member(memberDto);
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        if(member.getNickname().equals("manager")){
            member.setRoleManager();
        }else{
            member.setRoleUser();
        }

        memberRepository.save(member);
    }
}
