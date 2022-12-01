package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import com.plucas.graphql.exeption.ProblemAuthenticationException;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.*;
import com.plucas.graphql.service.command.SolutionCommandService;
import com.plucas.graphql.service.query.ProblemQueryService;
import com.plucas.graphql.service.query.UserQueryService;
import com.plucas.graphql.util.GraphqlBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import com.plucas.graphql.datasource.problems.entity.Solution;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

@DgsComponent
public class SolutionDataResolver {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private ProblemQueryService problemQueryService;

    @Autowired
    private SolutionCommandService solutionCommandService;

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionCreate)
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "solution") SolutionCreateInput input
    ) {
        var user = userQueryService.findByAuthToken(authToken).orElseThrow(ProblemAuthenticationException::new);

        var problemId = UUID.fromString(input.getProblemId());
        var problem = problemQueryService.findProblemById(problemId).orElseThrow(DgsEntityNotFoundException::new);
        var solution = GraphqlBeanMapper.mapToEntity(input, user, problem);

        var created = solutionCommandService.createSolution(solution);

        return SolutionResponse.newBuilder().solution(GraphqlBeanMapper.mapToGraphql(solution)).build();
    }


    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionVote)
    public SolutionResponse createSolutionVote(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "vote") SolutionVoteInput input
    ) {
        Optional<Solution> updated;
        userQueryService.findByAuthToken(authToken).orElseThrow(ProblemAuthenticationException::new);

        if (input.getVoteAsGood()) {
            updated = solutionCommandService.addVoteGoodCount(UUID.fromString(input.getSolutionId()));
        } else {
            updated = solutionCommandService.addVoteBadCount(UUID.fromString(input.getSolutionId()));
        }

        if (updated.isEmpty()) {
            throw new DgsEntityNotFoundException("Solution not found");
        }

        return SolutionResponse.newBuilder().solution(GraphqlBeanMapper.mapToGraphql(updated.get())).build();
    }


    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> solutionVoteChanged(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
