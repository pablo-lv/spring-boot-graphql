package com.plucas.graphql.exeption;

public class ProblemAuthenticationException extends RuntimeException {

    public ProblemAuthenticationException() {
        super("Invalid credential");
    }
}
