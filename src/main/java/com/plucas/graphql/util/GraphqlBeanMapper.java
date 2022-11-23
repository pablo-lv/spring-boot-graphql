package com.plucas.graphql.util;

import com.plucas.graphql.datasource.problems.entity.Problem;
import com.plucas.graphql.datasource.problems.entity.Solution;
import com.plucas.graphql.datasource.problems.entity.User;
import com.plucas.graphql.datasource.problems.entity.UserToken;
import com.plucas.graphql.generated.types.SolutionCategory;
import com.plucas.graphql.generated.types.UserAuthToken;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphqlBeanMapper {

    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(7);

    public static com.plucas.graphql.generated.types.User mapToGraphql(User original) {
        var result = new com.plucas.graphql.generated.types.User();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        result.setId(original.getId().toString());
        result.setAvatar(original.getAvatar());
        result.setCreateDateTime(createDateTime);
        result.setDisplayName(original.getDisplayName());
        result.setEmail(original.getEmail());
        result.setUsername(original.getUsername());
        return result;
    }

    public static com.plucas.graphql.generated.types.Problem mapToGraphql(Problem original) {
        var result = new com.plucas.graphql.generated.types.Problem();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());

        var convertedSolutions = original.getSolutions().stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .collect(Collectors.toList());

        var tagList = List.of(original.getTags().split(","));
        result.setContent(original.getContent());
        result.setAuthor(author);
        result.setCreateDateTime(createDateTime);
        result.setId(original.getId().toString());
        result.setSolutions(convertedSolutions);
        result.setTitle(original.getTitle());
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));
        result.setSolutionCount(convertedSolutions.size());
        result.setTags(new ArrayList<String>(Arrays.asList(original.getTags().split(","))));
        return result;
    }

    public static com.plucas.graphql.generated.types.Solution mapToGraphql(Solution original) {
        var result = new com.plucas.graphql.generated.types.Solution();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var category = StringUtils.equalsIgnoreCase(
                original.getCategory(), SolutionCategory.EXPLANATION.toString()) ?
                SolutionCategory.EXPLANATION : SolutionCategory.REFERENCE;

        result.setAuthor(author);
        result.setCategory(category);
        result.setContent(original.getContent());
        result.setId(original.getId().toString());
        result.setVoteAsBadCount(original.getVoteBadCount());
        result.setVoteAsGoodCount(original.getVoteGoodCount());
        result.setCreateDateTime(createDateTime);
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));
        return result;
    }

    public static com.plucas.graphql.generated.types.UserAuthToken mapToGraphql(UserToken original) {
        var result = new UserAuthToken();
        var expiryDateTime = original.getExpiryTimestamp().atOffset(ZONE_OFFSET);

        result.setAuthToken(original.getAuthToken());
        result.setExpiryTime(expiryDateTime);
        return result;
    }
}
