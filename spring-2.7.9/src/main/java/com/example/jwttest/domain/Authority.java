package com.example.jwttest.domain;

import com.example.jwttest.auth.MemberAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.example.jwttest.domain
 * fileName : Authority
 * author :  eomjin-ung
 * date : 2023/03/03
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/03           eomjin-ung          init
 */

@Entity
@Table(name = "authority")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    @Id
    @Column(name = "authority_name",length = 50)
    @Enumerated(EnumType.STRING)
    private MemberAuth authorityName;

    public String getAuthorityName() {
        return this.authorityName.toString();
    }
}