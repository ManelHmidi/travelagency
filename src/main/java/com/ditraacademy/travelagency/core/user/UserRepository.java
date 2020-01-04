package com.ditraacademy.travelagency.core.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByAgeIsLessThan(int age);
    Optional<User> findByUsername(String username);
}
