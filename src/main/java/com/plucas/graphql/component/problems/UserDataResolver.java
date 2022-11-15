package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.*;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    public User accountInfo(@RequestHeader(name = "authToken") String authToken) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserCreate)
    public UserResponse userCreate(@InputArgument(name = "user")UserCreateInput input) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserLogin)
    public UserResponse userLogin(@InputArgument(name = "user") UserLoginInput input) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserActivation)
    public UserActivationResponse userActivation(@InputArgument(name = "user") UserActivationInput input) {
        return null;
    }
}
