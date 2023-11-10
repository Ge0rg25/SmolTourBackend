package ru.umom.smolathonbackend.photos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.smolathonbackend.photos.models.PhotoEntity;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {
}
