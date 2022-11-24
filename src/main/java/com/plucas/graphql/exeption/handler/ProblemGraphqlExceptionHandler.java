package com.plucas.graphql.exeption.handler;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import com.plucas.graphql.exeption.ProblemAuthenticationException;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ProblemGraphqlExceptionHandler implements DataFetcherExceptionHandler {

    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handler) {
        var exception = handler.getException();

        if(exception instanceof ProblemAuthenticationException) {
            var graphqlError = TypedGraphQLError.newBuilder()
                    .message(exception.getMessage())
                    .path(handler.getPath())
                    .errorType(ErrorType.UNAUTHENTICATED)
                    .errorDetail(new ProblemErrorDetail())
                    .build();

            return DataFetcherExceptionHandlerResult.newResult().error(graphqlError).build();
        }

        return defaultHandler.onException(handler);
    }
}
