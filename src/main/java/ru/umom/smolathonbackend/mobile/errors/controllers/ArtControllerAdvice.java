package ru.umom.smolathonbackend.mobile.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.umom.smolathonbackend.mobile.errors.common.ArtNotExistsError;
import ru.umom.smolathonbackend.mobile.errors.common.PermissionArtDenindedError;
import ru.umom.smolathonbackend.utils.ErrorUtils;

@RestControllerAdvice
public class ArtControllerAdvice {

    @ExceptionHandler(value = {ArtNotExistsError.class})
    public ResponseEntity<?> onNotExists(){
        return ErrorUtils.genereateErrorResponse("art not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PermissionArtDenindedError.class})
    public ResponseEntity<?> permissionDenided(){
        return ErrorUtils.genereateErrorResponse("permission denided", HttpStatus.FORBIDDEN);
    }


}
