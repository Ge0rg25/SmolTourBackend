package ru.umom.smolathonbackend.web.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.smolathonbackend.web.dto.PreferenceDto;
import ru.umom.smolathonbackend.web.services.PreferenceService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/preferences")
public class PreferenceController {

    PreferenceService preferenceService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return preferenceService.getAll();
    }

    @PutMapping("/add")
    public ResponseEntity<?> addUserPreference(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated PreferenceDto.Request.AddUserPreference dto){
        return preferenceService.addUserPreference(jwt, dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserPreference(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated PreferenceDto.Request.DeleteUserPreference dto){
        return preferenceService.deleteUserPreference(jwt, dto);
    }

    @GetMapping("/get")
    public ResponseEntity<?> deleteUserPreference(@AuthenticationPrincipal Jwt jwt){
        return preferenceService.getUserPreferences(jwt);
    }

}
