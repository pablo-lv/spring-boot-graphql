package com.plucas.graphql.service.query;

import com.plucas.graphql.datasource.problems.entity.Problem;
import com.plucas.graphql.datasource.problems.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemQueryService {

    @Autowired
    private ProblemRepository problemRepository;

    public List<Problem> problemLatestList() {
        return problemRepository.findAllByOrderByCreationTimestampDesc();
    }

    public Optional<Problem> findProblemById(final UUID id) {
        return problemRepository.findById(id);
    }

    public List<Problem> problemsByKeyword(String keyword) {
        return problemRepository.findByKeyword("%"+ keyword +"%");
    }
}
