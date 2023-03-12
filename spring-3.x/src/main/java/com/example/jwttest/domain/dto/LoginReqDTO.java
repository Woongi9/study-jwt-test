package com.example.jwttest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * packageName :  com.example.jwttest.domain.dto
 * fileName : LoginReqDTO
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
public class LoginReqDTO {
    @NonNull
    private String email;

    @NonNull
    private String password;

}