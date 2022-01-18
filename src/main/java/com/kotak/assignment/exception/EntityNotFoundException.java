package com.kotak.assignment.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){super();}
    public EntityNotFoundException(String message){super(message);}
    public EntityNotFoundException(String message,Throwable ex){super(message,ex);}
    public EntityNotFoundException(Throwable ex){super(ex);}
}
