package study.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import study.jpa.dto.MemberDto;
import study.jpa.entity.Member;
import study.jpa.entity.Team;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);
        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findTop3HelloBy() {
        List<Member> helloBy = memberJpaRepository.findTop3HelloBy();
    }

    @Test
    public void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findUser("AAA", 10);
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<String> usernameList = memberJpaRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s= " + s);
        }
    }

    @Test
    public void findMemberDto() {
        // team을 먼저 save
        Team team = new Team("teamA");
        teamJpaRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberJpaRepository.save(m1);

        List<MemberDto> memberDtos = memberJpaRepository.findMemberDto();
        for (MemberDto dto : memberDtos) {
            System.out.println("dto= " + dto);
        }
    }

    @Test
    public void findBynames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member= " + member);
        }
    }

    @Test
    public void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        // List는 무조건 null이 아님. 값이 없더라도 빈 컬렉션 반환
        List<Member> aaa = memberJpaRepository.findListByUsername("AAA");
        Member findMember = memberJpaRepository.findMemberByUsername("AAA");
        System.out.println("findMember = " + findMember);

        Optional<Member> findOptional = memberJpaRepository.findOptionalByUsername("AAA");
        System.out.println("findOptional = " + findOptional);
    }

}
