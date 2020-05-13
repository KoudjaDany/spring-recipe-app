package com.ddf.training.springrecipeapp.controllers;

import com.ddf.training.springrecipeapp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling Not Found Exception.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception exception) {
        log.error("Handling Bad Request Exception.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("message", exception.getCause().getMessage());
        return modelAndView;
    }
}
