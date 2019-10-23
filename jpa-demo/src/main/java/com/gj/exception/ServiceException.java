package com.gj.exception;

/**
 * @author Gjing
 **/
public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
