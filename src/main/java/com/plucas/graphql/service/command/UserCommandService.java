package com.plucas.graphql.service.command;

import com.plucas.graphql.datasource.problems.entity.UserToken;
import com.plucas.graphql.datasource.problems.repository.UserRepository;
import com.plucas.graphql.datasource.problems.repository.UserTokenRepository;
import com.plucas.graphql.exeption.ProblemAuthenticationException;
import com.plucas.graphql.generated.types.UserAuthToken;
import com.plucas.graphql.util.HashUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserCommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;


    public UserToken login(String username, String password) {
        var userQueryResult = userRepository.findByUsername(username);

        if(userQueryResult.isEmpty() ||
                !HashUtil.isBcryptMatch(password, userQueryResult.get().getHashedPassword())) {
            throw new ProblemAuthenticationException();
        }

        var randomAuthToken = RandomStringUtils.randomAlphabetic(40);

        return refreshToken(userQueryResult.get().getId(), randomAuthToken);
    }


    private UserToken refreshToken(UUID userId, String authToken) {
        var userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setAuthToken(authToken);

        var now = LocalDateTime.now();
        userToken.setCreateTimestamp(now);
        userToken.setExpiryTimestamp(now.plusHours(2));

        return userTokenRepository.save(userToken);
    }

}
