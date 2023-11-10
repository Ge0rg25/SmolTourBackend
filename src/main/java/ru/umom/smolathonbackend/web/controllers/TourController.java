package ru.umom.smolathonbackend.web.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.smolathonbackend.web.dto.TourDto;
import ru.umom.smolathonbackend.web.services.TourService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/tours")
public class TourController {
    TourService tourService;

    @PutMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated TourDto.Request.Create dto){
        return tourService.createTour(jwt, dto);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated TourDto.Request.Update dto){
        return tourService.updateTour(jwt, dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated TourDto.Request.Delete dto){
        return tourService.deleteTour(jwt, dto);
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll(){
        return tourService.getAllTours();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@ModelAttribute TourDto.Request.Get dto){
        return tourService.getTourById(dto);
    }

}
