package com.example.jwttest.repository;

import com.example.jwttest.auth.MemberAuth;
import com.example.jwttest.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName :  com.example.jwttest.repository
 * fileName : AuthorityRepository
 * author :  eomjin-ung
 * date : 2023/03/03
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/03           eomjin-ung          init
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityName(MemberAuth authorityName);
}
