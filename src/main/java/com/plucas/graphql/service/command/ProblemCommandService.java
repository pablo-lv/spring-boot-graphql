package com.plucas.graphql.service.command;


import com.plucas.graphql.datasource.problems.entity.Problem;
import com.plucas.graphql.datasource.problems.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemCommandService {

    @Autowired
    private ProblemRepository problemRepository;

    public Problem createProblem(Problem problem) {
        var created = problemRepository.save(problem);
        return created;
    }
}
