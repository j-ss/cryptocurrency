package com.kotak.assignment.exception;

public class GenericException extends RuntimeException{
    public GenericException(){super();}
    public GenericException(String message){super(message);}
    public GenericException(String message,Throwable ex){super(message,ex);}
    public GenericException(Throwable ex){super(ex);}
}
