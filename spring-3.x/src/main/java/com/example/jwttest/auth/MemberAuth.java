package com.example.jwttest.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName :  com.example.jwttest.auth
 * fileName : MemberAuth
 * author :  eomjin-ung
 * date : 2023/03/03
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/03           eomjin-ung          init
 */
public enum MemberAuth {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),

    ;

    private final String abbreviation;

    private static final Map<String,MemberAuth> lookup = new HashMap<>();

    static {
        for(MemberAuth auth : MemberAuth.values()) {
            lookup.put(auth.abbreviation,auth);
        }
    }

    // private
    MemberAuth(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public static MemberAuth get(String abbreviation) {
        return lookup.get(abbreviation);
    }

    public static boolean containsKey(String abbreviation) {
        return lookup.containsKey(abbreviation);
    }

}