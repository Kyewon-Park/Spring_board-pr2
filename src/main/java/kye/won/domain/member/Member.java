package kye.won.domain.member;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String username;
    private String password;

    private Member() {
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
