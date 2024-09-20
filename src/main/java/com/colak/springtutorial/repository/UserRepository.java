package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.User;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @EntityGraph(value = "user[roles]")
    Optional<User> findRolesById(Long userId);

    @EntityGraph(value = "user[groups]")
    User findGroupsByUsername(String username);

    @Override
    @EntityGraph(value = "user[roles][groups]")
    Page<User> findAll(@NotNull Pageable pageable);
}
