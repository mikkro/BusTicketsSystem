package com.miko.busserver.Repository;

import com.miko.busserver.Entity.Role;
import com.miko.busserver.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User save(User user);

    Long countByRole(Role role);
}
