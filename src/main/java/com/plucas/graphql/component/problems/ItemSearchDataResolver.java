package com.plucas.graphql.component.problems;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.SearchItemFilter;
import com.plucas.graphql.generated.types.SearchableItem;

import java.util.List;

@DgsComponent
public class ItemSearchDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchableItems(@InputArgument(name = "filter")SearchItemFilter filter) {
        return null;
    }
}
