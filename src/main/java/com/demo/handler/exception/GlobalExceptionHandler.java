package com.demo.handler.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;  
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.bind.annotation.ResponseStatus;  
import org.springframework.web.bind.annotation.RestController; 

import com.demo.exception.BaseException;

@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {  
  
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = BaseException.class)  
    public void handleBaseException(BaseException e,HttpServletResponse response) throws IOException{ 
    	
    	  response.sendError(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
    	  
    }  
  
    @ExceptionHandler(value = Exception.class)  
    public void handleException(Exception e,HttpServletResponse response) throws IOException{
    	
    	response.sendError(HttpServletResponse.SC_CONFLICT,e.getMessage());
   }
}