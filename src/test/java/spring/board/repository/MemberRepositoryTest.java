package spring.board.repository;

import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Member;
import spring.board.dto.member.MemberDto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void init(){
        memberRepository.save(new Member(new MemberDto("testUser","1111","1111","testUser","user@user.com")));
    }

    @Test
    public void findUsername(){
        Member findMember = memberRepository.findByUsername("testUser");
        Member findUser = memberRepository.findByUsername("user");

        String usernameById = memberRepository.findUsernameById(findMember.getId());

        assertThat(usernameById).isEqualTo("testUser");
        assertThat(findUser.getUsername()).isEqualTo("user");
    }



}