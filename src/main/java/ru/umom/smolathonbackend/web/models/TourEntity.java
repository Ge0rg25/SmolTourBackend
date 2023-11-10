package ru.umom.smolathonbackend.web.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "TOURS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    String title;

    @Column
    String description;

    @Column
    String contact;

    @Column
    String company;

    @Column
    String photoId;

    @Column
    String ownerId;

    @ManyToMany
    @JoinTable(name = "tours_preferences",
            joinColumns = {@JoinColumn(name = "tour_id")},
            inverseJoinColumns = {@JoinColumn(name = "preference_id")})
    List<PreferenceEntity> preferences;
}
