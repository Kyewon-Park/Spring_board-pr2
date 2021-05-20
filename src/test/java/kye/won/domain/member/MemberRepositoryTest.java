package kye.won.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = new MemberRepository();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("ex", "pass");
        Member saved = memberRepository.save(member);
        Member found = memberRepository.findById(member.getId());
        assertThat(found).isEqualTo(saved);
    }

    @Test
    void findAll() {
        Member member1 = new Member("ex1", "pass");
        Member member2 = new Member("ex2", "pass");
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).contains(member1, member2);
    }

    @Test
    void updateMember() {
        Member member = new Member("ex", "pass");
        memberRepository.save(member);
        Member memberUpdated = new Member("exx", "passs");

        Member updated = memberRepository.updateMember(member.getId(), memberUpdated);
        assertThat(updated.getUsername()).isEqualTo(memberUpdated.getUsername());
    }
}