package se.lexicon.vxo.presence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PresenceControllerAdvice {

    private ModelAndView buildErrorModel(HttpStatus status, Exception exception){
        Map<Object, Object> errorMap = new HashMap<>();
        errorMap.put("name", status.getReasonPhrase());
        errorMap.put("code", status.value());
        errorMap.put("message", exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/errors/error-response");
        modelAndView.addObject("errorMap", errorMap);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex){
        return buildErrorModel(HttpStatus.BAD_REQUEST, ex);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(AppResourceNotFoundException.class)
    public ModelAndView handleAppResourceNotFoundException(AppResourceNotFoundException ex){
        return buildErrorModel(HttpStatus.NOT_FOUND, ex);
    }



}
