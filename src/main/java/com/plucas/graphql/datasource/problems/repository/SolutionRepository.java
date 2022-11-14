package com.plucas.graphql.datasource.problems.repository;

import com.plucas.graphql.datasource.problems.entity.Solution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, UUID> {
}
