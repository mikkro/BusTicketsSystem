package com.miko.busserver.Service.Impl;

import com.miko.busserver.Entity.Role;
import com.miko.busserver.Entity.User;
import com.miko.busserver.Repository.UserRepository;
import com.miko.busserver.Service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Map<String, Long> getNumbersOfUsers() {
        Map<String, Long> numbersOfUsers = new HashMap<>();
        numbersOfUsers.put("GUEST", userRepository.countByRole(Role.GUEST));
        numbersOfUsers.put("ADMIN", userRepository.countByRole(Role.ADMIN));
        numbersOfUsers.put("USER", userRepository.countByRole(Role.USER));
        return numbersOfUsers;
    }

    @Override
    public User findByUsernameById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
