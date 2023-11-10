package ru.umom.smolathonbackend.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.smolathonbackend.mobile.models.ArtEntity;


@Repository
public interface ArtRepository extends JpaRepository<ArtEntity, String> {
}


