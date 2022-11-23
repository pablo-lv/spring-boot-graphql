package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.*;
import com.plucas.graphql.service.command.UserCommandService;
import com.plucas.graphql.service.query.UserQueryService;
import com.plucas.graphql.util.GraphqlBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserCommandService userCommandService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    public User accountInfo(@RequestHeader(name = "authToken") String authToken) {
        var user = userQueryService.findByAuthToken(authToken).get();
        return GraphqlBeanMapper.mapToGraphql(user);
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserCreate)
    public UserResponse userCreate(@InputArgument(name = "user")UserCreateInput input) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserLogin)
    public UserResponse userLogin(@InputArgument(name = "user") UserLoginInput input) {
        var generatedToken = userCommandService.login(input.getUsername(), input.getPassword());

        var userAuthToken = GraphqlBeanMapper.mapToGraphql(generatedToken);
        var userInfo = accountInfo(userAuthToken.getAuthToken());
        var userResponse = UserResponse.newBuilder().authToken(userAuthToken).user(userInfo).build();

        return userResponse;

    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserActivation)
    public UserActivationResponse userActivation(@InputArgument(name = "user") UserActivationInput input) {
        return null;
    }
}
