package com.syno.word.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syno.word.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
