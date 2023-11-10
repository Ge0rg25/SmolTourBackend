package ru.umom.smolathonbackend.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Reviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    int rating;

    @Column
    String message;

    @Column
    String ownerName;

    @Column
    String ownerId;

    @Column
    String tourId;
}
