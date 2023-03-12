package com.example.jwttest;

import com.example.jwttest.auth.MemberAuth;
import com.example.jwttest.domain.Authority;
import com.example.jwttest.domain.Member;
import com.example.jwttest.domain.dto.LoginReqDTO;
import com.example.jwttest.domain.dto.MemberReqDTO;
import com.example.jwttest.domain.dto.TokenDTO;
import com.example.jwttest.exception.BizException;
import com.example.jwttest.exception.MemberExceptionType;
import com.example.jwttest.repository.AuthorityRepository;
import com.example.jwttest.repository.MemberRepository;
import com.example.jwttest.repository.RefreshTokenRepository;
import com.example.jwttest.service.AuthService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

/**
 * packageName :  com.example.jwttest
 * fileName : AuthServiceTest
 * author :  eomjin-ung
 * date : 2023/03/05
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/05           eomjin-ung          init
 */

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    EntityManager em;

    /**
     * 각 테스트 실행전에 실행
     */
    @BeforeEach
    public void beforeEach() {
        authorityRepository.save(new Authority(MemberAuth.ROLE_USER));
        authorityRepository.save(new Authority(MemberAuth.ROLE_ADMIN));

        MemberReqDTO dto = new MemberReqDTO();
        dto.setUsername("normalUser");
        dto.setEmail("normalUser@normalUser.com");
        dto.setPassword("1234");

        authService.signup(dto);
    }

    @DisplayName("회원가입이 정상적으로 된다")
    @Test
    @Transactional
    public void signup(){
        MemberReqDTO dto = new MemberReqDTO();
        dto.setUsername("user1");
        dto.setEmail("test@test.com");
        dto.setPassword("54321");

        authService.signup(dto);

        // 영속성 컨텍스트 플러쉬
        em.flush();
        em.clear();

        Optional<Member> ret = memberRepository.findByEmail("test@test.com");
        Assertions.assertThat(ret.get().getUsername()).isEqualTo("user1");
    }

    @DisplayName("이미 존재하는 회원인 경우 회원가입 불가")
    @Test
    @Transactional
    public void signupDuplicateMember() {
        MemberReqDTO dto = new MemberReqDTO();
        dto.setUsername("normalUser");
        dto.setEmail("normalUser@normalUser.com");
        dto.setPassword("1234");

        BizException bizException = assertThrows(BizException.class, () -> {
            authService.signup(dto);
        });

        // 이미 존재하는 사용자 입니다.
        Assertions.assertThat(bizException.getBaseExceptionType().getErrorCode()).isEqualTo("DUPLICATE_USER");
    }

    @DisplayName("이메일이 존재하지 않아 로그인에 실패한다.")
    @Test
    @Transactional
    public void loginFailBecauseNotFoundEmail() {
        //given
        LoginReqDTO dto = new LoginReqDTO();
        dto.setEmail("abc@abc.com");

        //when
        BizException bizException = assertThrows(BizException.class, () -> {
            authService.login(dto);
        });

        //then
        // 사용자를 찾을 수 없습니다.
        Assertions.assertThat(bizException.getBaseExceptionType().getErrorCode()).isEqualTo(MemberExceptionType.NOT_FOUND_USER.getErrorCode());
    }

    @DisplayName("비밀번호를 입력하지 않아 로그인 실패")
    @Test
    @Transactional
    public void loginFailBecauseEmptyPassword() {
        // given
        LoginReqDTO dto = new LoginReqDTO();
        dto.setEmail("normalUser@normalUser.com");

        //when
        BizException bizException = assertThrows(BizException.class, () -> {
            authService.login(dto);
        });

        //then
        // 비밀번호를 입력해주세요.
        Assertions.assertThat(bizException.getBaseExceptionType().getErrorCode())
                .isEqualTo(MemberExceptionType.NOT_FOUND_PASSWORD.getErrorCode());
    }

    @DisplayName("비밀번호가 틀려서 로그인 실패")
    @Test
    @Transactional
    public void loginFailBecauseWrongPassword() {
        // given
        LoginReqDTO dto = new LoginReqDTO();
        dto.setEmail("normalUser@normalUser.com");
        dto.setPassword("12345"); // right password : 1234

        //when
        BizException bizException = assertThrows(BizException.class, () -> {
            authService.login(dto);
        });

        //then
        // 비밀번호를 잘못 입력하였습니다.
        Assertions.assertThat(bizException.getBaseExceptionType().getErrorCode())
                .isEqualTo(MemberExceptionType.WRONG_PASSWORD.getErrorCode());
    }

    @DisplayName("로그인에 성공한다")
    @Test
    @Transactional
    public void loginSuccess() {
        // given
        LoginReqDTO dto = new LoginReqDTO();
        dto.setEmail("normalUser@normalUser.com");
        dto.setPassword("1234"); // right password : 1234

        TokenDTO login = authService.login(dto);
        Assertions.assertThat(login.getGrantType()).isEqualTo("Bearer");
    }
}
