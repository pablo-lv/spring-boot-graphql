package com.plucas.graphql.datasource.problems.repository;

import com.plucas.graphql.datasource.problems.entity.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, UUID> {
}
