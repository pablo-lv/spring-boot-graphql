package com.plucas.graphql.service.command;


import com.plucas.graphql.datasource.problems.entity.Problem;
import com.plucas.graphql.datasource.problems.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ProblemCommandService {

    private Sinks.Many<Problem> problemSink = Sinks.many().multicast().onBackpressureBuffer();

    @Autowired
    private ProblemRepository problemRepository;

    public Problem createProblem(Problem problem) {
        var created = problemRepository.save(problem);
        problemSink.tryEmitNext(created);
        return created;
    }


    public Flux<Problem> problemFlux() {
        return problemSink.asFlux();
    }
}
