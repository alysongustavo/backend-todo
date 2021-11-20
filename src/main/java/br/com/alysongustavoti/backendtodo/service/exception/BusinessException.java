package br.com.alysongustavoti.backendtodo.service.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }
}
