package ru.umom.smolathonbackend.web.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PREFERENCES")
public class PreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    String title;

    @ManyToMany(mappedBy = "preferences")
    List<TourEntity> tours;
}
