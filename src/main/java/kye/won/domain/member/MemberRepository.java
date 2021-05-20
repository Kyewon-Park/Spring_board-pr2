package kye.won.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {
    private final HashMap<String,Member> store = new HashMap<>();

    public Member save(Member member){
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(String id){
        Member found = store.get(id);
        return found;
    }

    public List<Member> findAll(){
        return new ArrayList<Member>(store.values());
    }

    public Member updateMember(String id, Member updateTo){
        Member member = store.get(id);
        member.setPassword(updateTo.getPassword());
        member.setUsername(updateTo.getUsername());
        return member;
    }

    public void clearStore(){
        store.clear();
    }
}
