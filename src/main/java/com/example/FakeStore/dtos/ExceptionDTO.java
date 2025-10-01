package com.example.FakeStore.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDTO {

    private HttpStatus errorCode;
    private String message;

    public HttpStatus getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionDTO(HttpStatus status, String message){
        this.errorCode = status;
        this.message = message;
    }


}
