package com.portafolio.auditai.repository;

import com.portafolio.auditai.entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserSecRepository extends JpaRepository<UserSec, Long> {
    Optional<UserSec> findByUsername(String username);
    boolean existsByUsername(String username);
}