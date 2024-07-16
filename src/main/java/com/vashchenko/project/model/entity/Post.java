package com.vashchenko.project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_uuid",updatable = false)
    UUID uuid;
    String data;
    @Column(updatable = false)
    Date creationDate;
    Date updateDate;
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    User user;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Post post = (Post) object;
        return Objects.equals(uuid, post.uuid) && Objects.equals(data, post.data) && Objects.equals(creationDate, post.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, data, creationDate);
    }
}
