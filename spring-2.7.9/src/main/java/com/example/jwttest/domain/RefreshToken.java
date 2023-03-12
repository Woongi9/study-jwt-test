package com.example.jwttest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.example.jwttest.token
 * fileName : RefreshToken
 * author :  eomjin-ung
 * date : 2023/03/03
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/03           eomjin-ung          init
 */
@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    @Id
    private String key;

    @Column(nullable = false)
    private String value;

    public void updateValue(String token) {
        this.value = token;
    }

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }
}