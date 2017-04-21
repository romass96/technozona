package ua.com.technozona.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.com.technozona.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {


    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(){
        return "error/404";
    }
}
