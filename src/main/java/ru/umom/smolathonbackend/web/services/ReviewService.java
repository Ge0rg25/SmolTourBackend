package ru.umom.smolathonbackend.web.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.smolathonbackend.web.dto.ReviewDto;
import ru.umom.smolathonbackend.web.models.ReviewEntity;
import ru.umom.smolathonbackend.web.repositories.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;

    public ResponseEntity<?> create(Jwt jwt, ReviewDto.Request.Create dto) {
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .rating(dto.rating())
                .message(dto.message())
                .ownerId(jwt.getSubject())
                .ownerName(jwt.getClaimAsString("name"))
                .tourId(dto.tourId())
                .build();
        reviewRepository.save(reviewEntity);

        ReviewDto.Response.BaseResponse response = new ReviewDto.Response.BaseResponse(
                reviewEntity.getId(),
                reviewEntity.getRating(),
                reviewEntity.getMessage(),
                reviewEntity.getOwnerName(),
                reviewEntity.getTourId(),
                reviewEntity.getOwnerId()
        );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> delete(Jwt jwt, ReviewDto.Request.Delete dto) {
        ReviewEntity reviewEntity = reviewRepository.findById(dto.id()).orElseThrow();

        if (!Objects.equals(jwt.getSubject(), reviewEntity.getOwnerId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        reviewRepository.delete(reviewEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getAllByTourId(ReviewDto.Request.GetAllByTourId dto) {
        List<ReviewEntity> reviewEntities = reviewRepository.findAllByTourId(dto.tourId());
        List<ReviewDto.Response.BaseResponse> response = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntities) {
            response.add(new ReviewDto.Response.BaseResponse(
                    reviewEntity.getId(),
                    reviewEntity.getRating(),
                    reviewEntity.getMessage(),
                    reviewEntity.getOwnerName(),
                    reviewEntity.getOwnerId(),
                    reviewEntity.getTourId()
            ));
        }
        return ResponseEntity.ok(response);
    }

}
