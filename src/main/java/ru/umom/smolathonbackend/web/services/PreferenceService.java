package ru.umom.smolathonbackend.web.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.smolathonbackend.web.dto.PreferenceDto;
import ru.umom.smolathonbackend.web.errors.common.PreferenceNotExistsError;
import ru.umom.smolathonbackend.web.errors.common.UserDontHavePreferenceError;
import ru.umom.smolathonbackend.web.models.PreferenceEntity;
import ru.umom.smolathonbackend.web.models.UserPreferenceEntity;
import ru.umom.smolathonbackend.web.repositories.PreferenceRepository;
import ru.umom.smolathonbackend.web.repositories.UserPreferenceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreferenceService {

    PreferenceRepository preferenceRepository;
    UserPreferenceRepository userPreferenceRepository;

    public ResponseEntity<?> getAll(){
        List<PreferenceEntity> preferences = preferenceRepository.findAll();
        List<PreferenceDto.Response.BaseResponse> response = new ArrayList<>();
        for (PreferenceEntity entity: preferences) {
            response.add(new PreferenceDto.Response.BaseResponse(entity.getId(), entity.getTitle()));
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> addUserPreference(Jwt jwt, PreferenceDto.Request.AddUserPreference dto){
        PreferenceEntity preferenceEntity = preferenceRepository.findById(dto.id()).orElseThrow(PreferenceNotExistsError::new);
        if(userPreferenceRepository.existsByUserIdAndAndPreference(jwt.getSubject(), preferenceEntity)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        UserPreferenceEntity userPreferenceEntity = UserPreferenceEntity.builder()
                .userId(jwt.getSubject())
                .preference(preferenceEntity)
                .build();
        userPreferenceRepository.save(userPreferenceEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUserPreference(Jwt jwt, PreferenceDto.Request.DeleteUserPreference dto){
        PreferenceEntity preferenceEntity  = preferenceRepository.findById(dto.id()).orElseThrow(PreferenceNotExistsError::new);
        UserPreferenceEntity userPreferenceEntity = userPreferenceRepository.findByPreferenceAndUserId(preferenceEntity, jwt.getSubject()).orElseThrow(UserDontHavePreferenceError::new);
        userPreferenceRepository.delete(userPreferenceEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getUserPreferences(Jwt jwt){
        List<UserPreferenceEntity> userPreferenceEntities = userPreferenceRepository.findAllByUserId(jwt.getSubject());
        List<PreferenceDto.Response.BaseResponse> response = new ArrayList<>();
        for(UserPreferenceEntity userPreferenceEntity: userPreferenceEntities){
            response.add(new PreferenceDto.Response.BaseResponse(userPreferenceEntity.getPreference().getId(), userPreferenceEntity.getPreference().getTitle()));
        }
        return ResponseEntity.ok(response);
    }

}
