package com.example.imkercloudserver.service.impl;

import com.example.imkercloudserver.repository.UserRepository;
import com.example.imkercloudserver.repository.entity.User;
import com.example.imkercloudserver.service.UserService;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(final Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> logIn(final User user) {
        final Optional<User> optionalUser = this.userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            final String hashedPassword = this.hashString(user.getPassword());
            if (optionalUser.get().getPassword().equals(hashedPassword)) {
                return optionalUser;
            }
        }
        return Optional.empty();
    }

    @Override
    public User save(final User user) {
        // IDE warns the the Hashing class is in "Beta", but API is stable and reliable.
        // Maybe "Beta" refers to a heads-up that the algorithm may change in the future.
        final String hashedPassword = this.hashString(user.getPassword());
        user.setPassword(hashedPassword);
        return this.userRepository.save(user);
    }

    @Override
    public User update(final User user) {
        final User entity = this.userRepository.getOne(user.getId());
        this.mapModelToEntity(user, entity);
        this.userRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteById(final Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void hashPassword(final String password) {
        final String hashedPassword = this.hashString(password);
        System.out.println(hashedPassword);
    }

    private void mapModelToEntity(final User model, final User entity) {
        if (model != null && entity != null) {
            entity.setFirstName(model.getFirstName());
            entity.setLastName(model.getLastName());
            entity.setEmail(model.getEmail());
        }
    }

    private String hashString(final String string) {
        return Hashing.sha256().hashString(string, StandardCharsets.UTF_8).toString();
    }
}
