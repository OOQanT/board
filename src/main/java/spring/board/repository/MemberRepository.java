package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUsername(String username);

    @Query("select m.username from Member m where m.id = :id")
    String findUsernameById(Long id);
}
