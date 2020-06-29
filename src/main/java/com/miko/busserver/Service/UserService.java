package com.miko.busserver.Service;

import com.miko.busserver.Entity.Seat;
import com.miko.busserver.Entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User findByUsername(String username);

    User saveUser(User user);

    List<User> findAllUsers();

    Map<String, Long> getNumbersOfUsers();

    User findByUsernameById(Long id);

    User findById(Long id);

    void deleteUser(User user);
}
