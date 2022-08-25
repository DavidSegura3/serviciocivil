package com.serviciocivil.gov.excepciones;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 3909350992999569190L;

    public NotFoundException(String message){
        super(message);
    }
}
