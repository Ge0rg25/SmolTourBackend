package ru.umom.smolathonbackend.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.umom.smolathonbackend.web.models.TourEntity;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, String> {
}
