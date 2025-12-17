package io.github.randallchang.repository;

import io.github.randallchang.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByUsername(String username);
}
