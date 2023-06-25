package com.kirti.springboot.utils;

import com.kirti.springboot.annotations.LazyComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

@LazyComponent
@AllArgsConstructor
@Getter
@Setter
public class CustomExceptionHandlerUtil extends RuntimeException{

    private String message ;
    private HttpStatus httpStatus;

    public CustomExceptionHandlerUtil(String message){
        super();
        this.message = message ;
    }
}
