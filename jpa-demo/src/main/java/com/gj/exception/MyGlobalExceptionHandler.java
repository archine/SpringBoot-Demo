package com.gj.exception;

import cn.gjing.tools.common.result.ErrorResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Gjing
 **/
@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity serviceException(ServiceException s) {
        return ResponseEntity.badRequest().body(ErrorResult.failure(s.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(ErrorResult.failure("操作失败"));
    }
}
