package ru.umom.smolathonbackend.web.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.smolathonbackend.web.dto.TourDto;
import ru.umom.smolathonbackend.web.errors.common.TourNotExistsError;
import ru.umom.smolathonbackend.web.models.PreferenceEntity;
import ru.umom.smolathonbackend.web.models.TourEntity;
import ru.umom.smolathonbackend.web.repositories.PreferenceRepository;
import ru.umom.smolathonbackend.web.repositories.TourRepository;

import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TourService {
    TourRepository tourRepository;
    PreferenceRepository preferenceRepository;

    public ResponseEntity<?> createTour(Jwt jwt, TourDto.Request.Create dto) {
        List<String> preferencesId = new ArrayList();
        if(dto.preferencesId() != null){
            preferencesId = dto.preferencesId();
        }
        List<PreferenceEntity> preferenceEntities = preferenceRepository.findAllById(preferencesId);

        TourEntity tourEntity = TourEntity.builder()
                .title(dto.title())
                .description(dto.description())
                .company(dto.company())
                .contact(dto.contact())
                .photoId(dto.photoId())
                .preferences(preferenceEntities)
                .ownerId(jwt.getSubject())
                .build();

        tourRepository.save(tourEntity);
        TourDto.Response.BaseResponse response = new TourDto.Response.BaseResponse(
                tourEntity.getId(),
                tourEntity.getTitle(),
                tourEntity.getDescription(),
                tourEntity.getContact(),
                tourEntity.getCompany(),
                tourEntity.getPhotoId(),
                tourEntity.getPreferences().stream().map(PreferenceEntity::getId).toList()
        );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> updateTour(Jwt jwt, TourDto.Request.Update dto) {
        TourEntity tourEntity = tourRepository.findById(dto.id()).orElseThrow(TourNotExistsError::new);

        if (!Objects.equals(tourEntity.getOwnerId(), jwt.getSubject())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<String> preferencesId = new ArrayList();
        if(dto.preferencesId() != null){
            preferencesId = dto.preferencesId();
        }
        List<PreferenceEntity> preferenceEntities = preferenceRepository.findAllById(preferencesId);

        tourEntity = TourEntity.builder()
                .id(tourEntity.getId())
                .title(dto.title())
                .description(dto.description())
                .company(dto.company())
                .contact(dto.contact())
                .photoId(dto.photoId())
                .preferences(preferenceEntities)
                .ownerId(jwt.getSubject())
                .build();

        tourRepository.save(tourEntity);
        TourDto.Response.BaseResponse response = new TourDto.Response.BaseResponse(
                tourEntity.getId(),
                tourEntity.getTitle(),
                tourEntity.getDescription(),
                tourEntity.getContact(),
                tourEntity.getCompany(),
                tourEntity.getPhotoId(),
                tourEntity.getPreferences().stream().map(PreferenceEntity::getId).toList()
        );
        return ResponseEntity.ok(response);

    }

    public ResponseEntity<?> deleteTour(Jwt jwt, TourDto.Request.Delete dto) {
        TourEntity tourEntity = tourRepository.findById(dto.id()).orElseThrow(TourNotExistsError::new);
        if (!Objects.equals(tourEntity.getOwnerId(), jwt.getSubject())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        tourRepository.delete(tourEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getAllTours() {
        List<TourEntity> tours = tourRepository.findAll();
        Set<TourDto.Response.BaseResponse> response = new HashSet<>();
        for (TourEntity tour : tours) {
            response.add(new TourDto.Response.BaseResponse(
                    tour.getId(),
                    tour.getTitle(),
                    tour.getDescription(),
                    tour.getContact(),
                    tour.getCompany(),
                    tour.getPhotoId(),
                    tour.getPreferences().stream().map(PreferenceEntity::getId).toList()
            ));
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getTourById(TourDto.Request.Get dto) {
        TourEntity tourEntity = tourRepository.findById(dto.id()).orElseThrow(TourNotExistsError::new);

        TourDto.Response.BaseResponse response = new TourDto.Response.BaseResponse(
                tourEntity.getId(),
                tourEntity.getTitle(),
                tourEntity.getDescription(),
                tourEntity.getContact(),
                tourEntity.getCompany(),
                tourEntity.getPhotoId(),
                tourEntity.getPreferences().stream().map(PreferenceEntity::getId).toList()
        );
        return ResponseEntity.ok(response);

    }

}
