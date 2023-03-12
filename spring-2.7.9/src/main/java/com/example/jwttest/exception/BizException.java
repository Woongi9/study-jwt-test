package com.example.jwttest.exception;

import lombok.Getter;

/**
 * packageName :  com.example.jwttest.exception
 * fileName : BizException
 * author :  eomjin-ung
 * date : 2023/03/05
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/05           eomjin-ung          init
 */
@Getter
public class BizException extends RuntimeException{
    private final BaseExceptionType baseExceptionType;

    public BizException(BaseExceptionType baseExceptionType){
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

}