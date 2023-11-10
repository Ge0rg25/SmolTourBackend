package ru.umom.smolathonbackend.web.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.smolathonbackend.web.dto.ReviewDto;
import ru.umom.smolathonbackend.web.services.ReviewService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    ReviewService reviewService;

    @GetMapping("/get/{tourId}")
    public ResponseEntity<?> getAllByTourId(@RequestBody @Validated ReviewDto.Request.GetAllByTourId dto){
        return reviewService.getAllByTourId(dto);
    }

    @PutMapping("/create")
    public ResponseEntity<?> create(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated ReviewDto.Request.Create dto){
        return reviewService.create(jwt, dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Jwt jwt, @RequestBody @Validated ReviewDto.Request.Delete dto){
        return reviewService.delete(jwt, dto);
    }


}
