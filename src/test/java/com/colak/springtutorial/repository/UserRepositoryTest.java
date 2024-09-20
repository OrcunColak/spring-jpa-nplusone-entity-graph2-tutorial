package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.Group;
import com.colak.springtutorial.jpa.Role;
import com.colak.springtutorial.jpa.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findRolesById() {
        Optional<User> byId = repository.findRolesById(1L);
        assertThat(byId).isPresent();

        User user = byId.get();
        assertThat(user.getRoles())
                .extracting(Role::getName)
                .containsExactlyInAnyOrder("Admin Role");
    }

    @Test
    void findGroupsByUsername() {
        User user = repository.findGroupsByUsername("john_doe");

        assertThat(user.getGroups())
                .extracting(Group::getName)
                .containsExactlyInAnyOrder("Admins");
    }

    @Test
    void findAll() {
        // SELECT *
        // FROM my_users u1_0
        // LEFT JOIN my_users_groups g1_0 ON u1_0.id = g1_0.user_id
        // LEFT JOIN my_groups g1_1 ON g1_1.id = g1_0.group_id
        // LEFT JOIN my_users_roles r1_0 ON u1_0.id = r1_0.user_id
        // LEFT JOIN my_roles r1_1 ON r1_1.id = r1_0.role_id;

        Pageable pageable = Pageable.ofSize(1);
        Page<User> userPage = repository.findAll(pageable);
        List<User> userList = userPage.toList();

        User firstUser = userList.getFirst();

        assertThat(firstUser.getRoles())
                .extracting(Role::getName)
                .containsExactlyInAnyOrder("Admin Role");

        assertThat(firstUser.getGroups())
                .extracting(Group::getName)
                .containsExactlyInAnyOrder("Admins");

    }
}