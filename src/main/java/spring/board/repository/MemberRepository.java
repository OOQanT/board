package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUsername(String username);

}
