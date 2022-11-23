package com.plucas.graphql.service.query;

import com.plucas.graphql.datasource.problems.entity.User;
import com.plucas.graphql.datasource.problems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByAuthToken(String authToken) {
        return userRepository.findByAuthToken(authToken);
    }
}
