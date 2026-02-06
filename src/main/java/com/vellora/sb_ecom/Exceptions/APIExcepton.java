package com.vellora.sb_ecom.Exceptions;

public class APIExcepton extends  RuntimeException {

    private static final long serialVersionUID = 1L;

    public APIExcepton(){

    }

    public APIExcepton(String message){
        super(message);
    }
}
