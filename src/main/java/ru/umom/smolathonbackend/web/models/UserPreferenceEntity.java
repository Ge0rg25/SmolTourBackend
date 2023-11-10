package ru.umom.smolathonbackend.web.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS_PREFERENCES")
public class UserPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column
    String userId;

    @ManyToOne
    @JoinColumn(name = "preference_id")
    PreferenceEntity preference;
}
