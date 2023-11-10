package ru.umom.smolathonbackend.web.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.umom.smolathonbackend.utils.ErrorUtils;
import ru.umom.smolathonbackend.web.errors.common.ReviewNotExistsError;

@RestControllerAdvice
public class ReviewErrorController {

    @ExceptionHandler(value = {ReviewNotExistsError.class})
    public ResponseEntity<?> onReviewNotExists() {
        return ErrorUtils.genereateErrorResponse("review not exists", HttpStatus.BAD_REQUEST);
    }

}
