package com.vashchenko.project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_uuid", updatable = false)
    UUID uuid;
    @Column(updatable = false)
    String username;
    @Column(updatable = false)
    String email;
    @Column(updatable = false)
    Date registrationDate;
    @Column(updatable = false)
    Date birthDate;
    @Column(updatable = false)
    String passwordHash;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    Set<Post> posts;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(uuid, user.uuid) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(birthDate, user.birthDate) && Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, username, email, registrationDate, birthDate, passwordHash);
    }
}
