package ru.umom.smolathonbackend.mobile.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Arts")
public class ArtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    String ownerId;

    @Column
    String title;

    @Column
    String description;

    @Column
    String imageId;

    @Column
    double lat;

    @Column
    double lon;
}
