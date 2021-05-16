package com.example.imkercloudserver.service;

import com.example.imkercloudserver.exception.BusinessException;
import com.example.imkercloudserver.repository.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findAll();

    Optional<User> findById(final Long id);

    Optional<User> findByUsername(final String username);

    Optional<User> logIn(final User user) throws BusinessException;

    User save(final User user);

    User update(final User user);

    void deleteById(final Long id);
}
