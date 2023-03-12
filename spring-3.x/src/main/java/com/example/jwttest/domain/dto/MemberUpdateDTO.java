package com.example.jwttest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName :  com.example.jwttest.domain.dto
 * fileName : MemberUpdateDTO
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
public class MemberUpdateDTO {
    private String email;
    private String password;
    private String username;
    private List<String> authorities;

}