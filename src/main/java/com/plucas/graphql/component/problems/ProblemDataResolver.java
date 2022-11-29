package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.exeption.ProblemAuthenticationException;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.Problem;
import com.plucas.graphql.generated.types.ProblemCreateInput;
import com.plucas.graphql.generated.types.ProblemResponse;
import com.plucas.graphql.service.command.ProblemCommandService;
import com.plucas.graphql.service.query.ProblemQueryService;
import com.plucas.graphql.service.query.UserQueryService;
import com.plucas.graphql.util.GraphqlBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class ProblemDataResolver {

    @Autowired
    private ProblemQueryService problemQueryService;

    @Autowired
    private ProblemCommandService problemCommandService;

    @Autowired
    private UserQueryService userQueryService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemLatestList)
    public List<Problem> getProblemLatestList() {

        return problemQueryService.problemLatestList().stream().map(GraphqlBeanMapper::mapToGraphql)
                .collect(Collectors.toList());

    }


    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemDetail)
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId) {

        Optional<com.plucas.graphql.datasource.problems.entity.Problem> problem =
                problemQueryService.findProblemById(UUID.fromString(problemId));
        if (problem.isPresent()) {
            return GraphqlBeanMapper.mapToGraphql(problem.get());
        }

        return Problem.newBuilder().build();
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "problem")ProblemCreateInput input
            ) {

        var user = userQueryService.findByAuthToken(authToken).orElseThrow(ProblemAuthenticationException::new);

        var problem = GraphqlBeanMapper.mapToEntity(input, user);

        var created = problemCommandService.createProblem(problem);


        return ProblemResponse.newBuilder().problem(GraphqlBeanMapper.mapToGraphql(created)).build();
    }


    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> subscriptionProblemAdded() {
        return null;
    }

}
