package ru.umom.smolathonbackend.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.smolathonbackend.web.models.PreferenceEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreferenceRepository extends JpaRepository<PreferenceEntity, String> {
}
