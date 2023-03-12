package com.example.jwttest.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName :  com.example.jwttest.exception
 * fileName : BaseExceptionType
 * author :  eomjin-ung
 * date : 2023/03/05
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/05           eomjin-ung          init
 */
public interface BaseExceptionType {
    String getErrorCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
