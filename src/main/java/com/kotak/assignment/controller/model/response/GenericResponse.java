package com.kotak.assignment.controller.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    private String message;
    private T data;
    private String error;
    private String path;

    public GenericResponse(String message,T data,String error){
        this.message = message;
        this.data = data;
        this.error= error;
    }
}

