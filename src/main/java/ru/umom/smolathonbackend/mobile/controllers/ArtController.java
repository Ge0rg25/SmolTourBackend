package ru.umom.smolathonbackend.mobile.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.smolathonbackend.mobile.dto.ArtDto;
import ru.umom.smolathonbackend.mobile.services.ArtService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/arts")
public class ArtController {

    ArtService artService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return artService.getAll();
    }

    @PutMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated ArtDto.Request.Create dto){
        return artService.create(jwt, dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated ArtDto.Request.Delete dto){
        return artService.delete(jwt, dto);
    }

}
