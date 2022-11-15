package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.*;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
public class SolutionDataResolver {

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionCreate)
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "newSolution") SolutionCreateInput input
    ) {
        return null;
    }


    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionVote)
    public SolutionResponse Vote(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "newVote") SolutionVoteInput input
    ) {
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> solutionVoteChanged(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
