package com.example.jwttest.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * packageName :  com.example.jwttest.token
 * fileName : CustomEmailPasswordAuthToken
 * author :  eomjin-ung
 * date : 2023/03/03
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/03           eomjin-ung          init
 */
public class CustomEmailPasswordAuthToken extends AbstractAuthenticationToken {

    private final Object principal;

    private Object credentials;

    public CustomEmailPasswordAuthToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public CustomEmailPasswordAuthToken(Object principal, Object credentials,
                                        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
