package ru.umom.smolathonbackend.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.smolathonbackend.web.models.PreferenceEntity;
import ru.umom.smolathonbackend.web.models.UserPreferenceEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreferenceEntity, String> {
    Boolean existsByUserIdAndAndPreference(String userId, PreferenceEntity preference);
    List<UserPreferenceEntity> findAllByUserId(String userId);

    Optional<UserPreferenceEntity> findByPreferenceAndUserId(PreferenceEntity preference, String userId);
}
