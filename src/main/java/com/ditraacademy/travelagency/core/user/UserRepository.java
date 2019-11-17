package com.ditraacademy.travelagency.core.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByAgeIsLessThan(int age);
}