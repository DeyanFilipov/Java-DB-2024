package org.AutoMappingObjectsExercise.data.entities.base;

import jakarta.persistence.*;
import org.AutoMappingObjectsExercise.data.entities.Game;
import org.AutoMappingObjectsExercise.data.entities.User;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany
    private Set<Game> games;
}