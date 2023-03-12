package com.example.jwttest.domain.dto;

import com.example.jwttest.domain.Authority;
import com.example.jwttest.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * packageName :  com.example.jwttest.domain.dto
 * fileName : MemberReqDTO
 * author :  eomjin-ung
 * date : 2023/03/05
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/05           eomjin-ung          init
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReqDTO {
    private String email;
    private String password;
    private String username;

    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
        return Member.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .activated(false)
                .authorities(authorities)
                .build();
    }

}
