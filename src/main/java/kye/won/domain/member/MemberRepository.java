package kye.won.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {
    private final HashMap<Long,Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }

    public Member findById(Long id){
        Member found = store.get(id);
        return found;
    }

    public List<Member> findAll(){
        return new ArrayList<Member>(store.values());
    }

    public Member updateMember(Long id, Member updateTo){
        Member member = store.get(id);
        member.setUsername(updateTo.getUsername());
        member.setPassword(updateTo.getPassword());
        return member;
    }

    public void clearStore(){
        store.clear();
    }
}
