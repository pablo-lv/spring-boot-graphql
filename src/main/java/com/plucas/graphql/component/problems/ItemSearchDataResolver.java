package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.SearchItemFilter;
import com.plucas.graphql.generated.types.SearchableItem;
import com.plucas.graphql.service.query.ProblemQueryService;
import com.plucas.graphql.service.query.SolutionQueryService;
import com.plucas.graphql.util.GraphqlBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ItemSearchDataResolver {

    @Autowired
    private ProblemQueryService problemQueryService;

    @Autowired
    private SolutionQueryService solutionQueryService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    private List<SearchableItem> searchItems(@InputArgument(name = "filter") SearchItemFilter filter) {
        var result = new ArrayList<SearchableItem>();
        var keyword = filter.getKeyword();

        var problemsByKeyword = problemQueryService.problemsByKeyword(keyword).stream()
                .map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());
        var solutionsByKeyword = solutionQueryService.solutionsByKeyword(keyword).stream()
                .map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());

        result.addAll(problemsByKeyword);
        result.addAll(solutionsByKeyword);

        if (result.isEmpty()) {
            throw new DgsEntityNotFoundException("Not item with keyword " + keyword);
        }

        result.sort(
                Comparator.comparing(SearchableItem::getCreateDateTime).reversed()
        );
        return result;
    }
}
