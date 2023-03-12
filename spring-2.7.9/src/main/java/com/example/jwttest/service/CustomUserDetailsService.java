package com.example.jwttest.service;

import com.example.jwttest.domain.Authority;
import com.example.jwttest.domain.Member;
import com.example.jwttest.exception.BizException;
import com.example.jwttest.exception.MemberExceptionType;
import com.example.jwttest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName :  com.example.jwttest.service
 * fileName : CustomUserDetailsService
 * author :  eomjin-ung
 * date : 2023/03/04
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/04           eomjin-ung          init
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws BizException{
        log.debug("CustomUserDetailsService -> email = {}", email);

        return memberRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BizException(MemberExceptionType.NOT_FOUND_USER));
    }

    @Transactional(readOnly = true)
    public Member getMember(String email) throws BizException{
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BizException(MemberExceptionType.NOT_FOUND_USER));
    }

    private UserDetails createUserDetails(Member member) {

        List<SimpleGrantedAuthority> authorityList = member.getAuthorities()
                .stream()
                .map(Authority::getAuthorityName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        authorityList.forEach(a -> log.debug("authorityList -> {}", a.getAuthority()));

        return new User(
                member.getEmail(),
                member.getPassword(),
                authorityList
        );
    }
}
