package com.plucas.graphql.service.command;

import com.plucas.graphql.datasource.problems.entity.Solution;
import com.plucas.graphql.datasource.problems.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionCommandService {

    @Autowired
    private SolutionRepository solutionRepository;

    public Solution createSolution(Solution solution) {
        var created = solutionRepository.save(solution);
        return created;
    }

    public Optional<Solution> addVoteBadCount(UUID solutionId) {
        solutionRepository.addVoteBadCount(solutionId);
        var updated = solutionRepository.findById(solutionId);

        return updated;
    }

    public Optional<Solution> addVoteGoodCount(UUID solutionId) {
        solutionRepository.addVoteGoodCount(solutionId);
        var updated = solutionRepository.findById(solutionId);

        return updated;
    }
}
