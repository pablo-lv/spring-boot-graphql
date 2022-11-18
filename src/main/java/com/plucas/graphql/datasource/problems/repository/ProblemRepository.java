package com.plucas.graphql.datasource.problems.repository;

import com.plucas.graphql.datasource.problems.entity.Problem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, UUID> {

    List<Problem> findAllByOrderByCreationTimestampDesc();

    @Query(nativeQuery = true, value = "select * from problems p " +
            "where upper(content) like upper(:keyword) " +
            "or upper(title) like upper(:keyword) " +
            "or upper(tags) like upper(:keyword)")
    List<Problem> findByKeyword(@Param("keyword") String keyword);
}
