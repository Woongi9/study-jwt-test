package com.example.jwttest.token;

import com.example.jwttest.exception.BizException;
import com.example.jwttest.exception.MemberExceptionType;
import com.example.jwttest.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * packageName :  com.example.jwttest.token
 * fileName : CustomEmailPasswordAuthProvider
 * author :  eomjin-ung
 * date : 2023/03/04
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/04           eomjin-ung          init
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomEmailPasswordAuthProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    protected void additionalAuthenticationChecks(UserDetails userDetails, CustomEmailPasswordAuthToken authentication) throws BizException{
        log.debug("additional AuthenticationChecks authentication = {}", authentication);

        if (authentication.getCredentials() == null) {
            log.debug("additional AuthenticationChecks is null!");
            throw new BizException(MemberExceptionType.NOT_FOUND_PASSWORD);
        }

        String presentedPassword = authentication.getCredentials().toString();
        log.debug("authentication.presentedPassword = {}", presentedPassword);

        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            throw new BizException(MemberExceptionType.WRONG_PASSWORD);
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws BizException {
        UserDetails user = null;

        try {
            user = retrieveUser(authentication.getName());
        } catch (BizException ex) {
            throw ex;
        }

        Object principalToReturn = user;
        CustomEmailPasswordAuthToken result = new CustomEmailPasswordAuthToken(principalToReturn
                , authentication.getCredentials()
                , this.authoritiesMapper.mapAuthorities(user.getAuthorities()
        ));

        additionalAuthenticationChecks(user, result);
        result.setDetails(authentication.getDetails());
        return result;
    }

    protected final UserDetails retrieveUser(String username ) throws BizException {
        try {
            UserDetails loadedUser = userDetailsService.loadUserByUsername(username);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        }
        catch (BizException ex) {
            log.debug("error in retrieveUser = {}", ex.getMessage());
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalAuthenticationServiceException(
                    "내부 인증 로직중 알수 없는 오류가 발생하였습니다.");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomEmailPasswordAuthToken.class);
    }
}
