package ru.umom.smolathonbackend.web.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.umom.smolathonbackend.utils.ErrorUtils;
import ru.umom.smolathonbackend.web.errors.common.TourNotExistsError;

@RestControllerAdvice
public class TourErrorController {

    @ExceptionHandler({TourNotExistsError.class})
    public ResponseEntity<?> tourNotExists(){
        return ErrorUtils.genereateErrorResponse("tour not exists", HttpStatus.BAD_REQUEST);
    }
}
