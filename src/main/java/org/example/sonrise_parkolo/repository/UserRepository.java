package org.example.sonrise_parkolo.repository;

import org.example.sonrise_parkolo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
