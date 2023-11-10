package ru.umom.smolathonbackend.web.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.umom.smolathonbackend.utils.ErrorUtils;
import ru.umom.smolathonbackend.web.errors.common.PreferenceNotExistsError;
import ru.umom.smolathonbackend.web.errors.common.UserDontHavePreferenceError;

@RestControllerAdvice
public class PreferenceErrorController {

    @ExceptionHandler(value = {PreferenceNotExistsError.class})
    public ResponseEntity<?> contentTypeNotAllowed(RuntimeException exception, WebRequest webRequest){
        return ErrorUtils.genereateErrorResponse("preference not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserDontHavePreferenceError.class})
    public ResponseEntity<?> userDontHavePreference(){
        return ErrorUtils.genereateErrorResponse("you dont have this preference", HttpStatus.BAD_REQUEST);
    }
}
