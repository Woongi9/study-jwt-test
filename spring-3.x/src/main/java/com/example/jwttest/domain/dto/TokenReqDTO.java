package com.example.jwttest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.example.jwttest.domain.dto
 * fileName : TokenReqDTO
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
public class TokenReqDTO {
    private String accessToken;
    private String refreshToken;
}