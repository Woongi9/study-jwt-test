package com.example.jwttest.domain.dto;

import com.example.jwttest.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName :  com.example.jwttest.domain.dto
 * fileName : MemberRespDTO
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
public class MemberRespDTO {
    private String email;

    public static MemberRespDTO of(Member member) {
        return new MemberRespDTO(member.getEmail());
    }
}