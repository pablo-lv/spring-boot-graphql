package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.Problem;
import com.plucas.graphql.generated.types.ProblemCreateInput;
import com.plucas.graphql.generated.types.ProblemResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;

@DgsComponent
public class ProblemDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemLatestList)
    public List<Problem> getProblemLatestList() {
        return null;
    }


    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemDetail)
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "problem")ProblemCreateInput input
            ) {
        return null;
    }


    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> subscriptionProblemAdded() {
        return null;
    }

}
