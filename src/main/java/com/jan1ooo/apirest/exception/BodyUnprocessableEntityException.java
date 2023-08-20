package com.jan1ooo.apirest.exception;

public class BodyUnprocessableEntityException extends RuntimeException {

    public BodyUnprocessableEntityException(String msg){
        super("A transação não foi aceita pelo motivo: " + msg);
    }
}
