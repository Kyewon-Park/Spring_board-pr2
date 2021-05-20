package kye.won.domain.member;

import lombok.Data;

@Data
public class Member {
    private String id;
    private String password;
    private String username;

    private Member() {
    }

    public Member(String id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }
}
