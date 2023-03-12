package com.example.jwttest.controller;

import com.example.jwttest.domain.dto.*;
import com.example.jwttest.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName :  com.example.jwttest.controller
 * fileName : AuthController
 * author :  eomjin-ung
 * date : 2023/03/04
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/04           eomjin-ung          init
 */

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public MemberRespDTO signup(@RequestBody MemberReqDTO memberRequestDto) {
        log.debug("memberRequestDto = {}",memberRequestDto);
        return authService.signup(memberRequestDto);
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginReqDTO loginReqDTO) {
        return authService.login(loginReqDTO);
    }

    @PostMapping("/reissue")
    public TokenDTO reissue(@RequestBody TokenReqDTO tokenRequestDto) {
        return authService.reissue(tokenRequestDto);
    }
}