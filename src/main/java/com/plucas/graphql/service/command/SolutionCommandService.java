package com.plucas.graphql.service.command;

import com.plucas.graphql.datasource.problems.entity.Solution;
import com.plucas.graphql.datasource.problems.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionCommandService {

    private Sinks.Many<Solution> solutionSink = Sinks.many().multicast().onBackpressureBuffer();

    @Autowired
    private SolutionRepository solutionRepository;

    public Solution createSolution(Solution solution) {
        var created = solutionRepository.save(solution);
        return created;
    }

    public Optional<Solution> addVoteBadCount(UUID solutionId) {
        solutionRepository.addVoteBadCount(solutionId);
        var updated = solutionRepository.findById(solutionId);

        updated.ifPresent(solution -> solutionSink.tryEmitNext(solution));

        return updated;
    }

    public Optional<Solution> addVoteGoodCount(UUID solutionId) {
        solutionRepository.addVoteGoodCount(solutionId);
        var updated = solutionRepository.findById(solutionId);

        updated.ifPresent(solution -> solutionSink.tryEmitNext(solution));

        return updated;
    }

    public Flux<Solution> solutionFlux() {
        return solutionSink.asFlux();
    }

}
