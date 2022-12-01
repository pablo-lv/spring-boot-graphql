package com.plucas.graphql.datasource.problems.repository;

import com.plucas.graphql.datasource.problems.entity.Solution;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, UUID> {

    @Query(nativeQuery = true, value = "select * from solutions s where upper(content) like upper(:keyword)")
    public List<Solution> findByKeyword(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update solutions set vote_bad_count = vote_bad_count + 1 where id=:solutionId")
    void addVoteBadCount(@Param("solutionId") UUID id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update solutions set vote_good_count = vote_bad_count + 1 where id=:solutionId")
    void addVoteGoodCount(@Param("solutionId") UUID id);
}
