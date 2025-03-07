package org.AutoMappingObjectsExercise.data.repositories;

import org.AutoMappingObjectsExercise.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
