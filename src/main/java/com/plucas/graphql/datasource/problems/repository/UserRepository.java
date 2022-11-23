package com.plucas.graphql.datasource.problems.repository;

import com.plucas.graphql.datasource.problems.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value = "" +
            "select u.* from users u inner join users_token ut " +
            "on u.id = ut.user_id " +
            "where ut.auth_token = :authToken " +
            "and ut.expiry_timestamp > current_timestamp")
    Optional<User> findByAuthToken(String authToken);
}
