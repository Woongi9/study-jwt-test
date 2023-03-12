package com.example.jwttest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.example.jwttest.domain.dto
 * fileName : TokenDTO
 * author :  eomjin-ung
 * date : 2023/03/05
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/05           eomjin-ung          init
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}