package study.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import study.jpa.entity.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}
