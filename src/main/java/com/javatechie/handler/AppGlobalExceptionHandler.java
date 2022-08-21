package com.javatechie.handler;

import com.javatechie.dto.ErrorDTO;
import com.javatechie.dto.ServiceResponse;
import exception.CourseServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AppGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception){
        ServiceResponse<?> serviceResponse=new ServiceResponse<>();
        List<ErrorDTO> errorDTOS=new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
                    errorDTOS.add(new ErrorDTO(error.getDefaultMessage()));
                });
        serviceResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setErrorDTO(errorDTOS);
        return serviceResponse;
    }

    @ExceptionHandler(CourseServiceException.class)
    public ServiceResponse<?> handleCourseServiceException(CourseServiceException exception){
        ServiceResponse<?> serviceResponse=new ServiceResponse<>();
        List<ErrorDTO> errorDTOS=new ArrayList<>();
        errorDTOS.add(new ErrorDTO(exception.getMessage()));
        serviceResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setErrorDTO(errorDTOS);
        return serviceResponse;
    }
}
