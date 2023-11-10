package ru.umom.smolathonbackend.mobile.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.smolathonbackend.mobile.dto.ArtDto;
import ru.umom.smolathonbackend.mobile.errors.common.ArtNotExistsError;
import ru.umom.smolathonbackend.mobile.errors.common.PermissionArtDenindedError;
import ru.umom.smolathonbackend.mobile.models.ArtEntity;
import ru.umom.smolathonbackend.mobile.repositories.ArtRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ArtService {
    ArtRepository artRepository;

    public ResponseEntity<?> create(Jwt jwt, ArtDto.Request.Create dto) {
        ArtEntity artEntity = ArtEntity.builder()
                .ownerId(jwt.getSubject())
                .title(dto.title())
                .description(dto.description())
                .imageId(dto.imageId())
                .lat(dto.lat())
                .lon(dto.lon())
                .build();
        artRepository.save(artEntity);

        ArtDto.Response.BaseResponse response = new ArtDto.Response.BaseResponse(
                artEntity.getId(),
                artEntity.getOwnerId(),
                artEntity.getTitle(),
                artEntity.getDescription(),
                artEntity.getImageId(),
                artEntity.getLat(),
                artEntity.getLon()
        );
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> delete(Jwt jwt, ArtDto.Request.Delete dto) {
        ArtEntity artEntity = artRepository.findById(dto.id()).orElseThrow(ArtNotExistsError::new);


        if (!Objects.equals(artEntity.getOwnerId(), jwt.getSubject())) {
            throw new PermissionArtDenindedError();
        }

        artRepository.delete(artEntity);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> getAll() {
        List<ArtEntity> artEntities = artRepository.findAll();
        List<ArtDto.Response.BaseResponse> response = new ArrayList<>();
        for (ArtEntity artEntity : artEntities) {
            response.add(
                    new ArtDto.Response.BaseResponse(
                            artEntity.getId(),
                            artEntity.getOwnerId(),
                            artEntity.getTitle(),
                            artEntity.getDescription(),
                            artEntity.getImageId(),
                            artEntity.getLat(),
                            artEntity.getLon()
                    )
            );
        }
        return ResponseEntity.ok(response);
    }

}
