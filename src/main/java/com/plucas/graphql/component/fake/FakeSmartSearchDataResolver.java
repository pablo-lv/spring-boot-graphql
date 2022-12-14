package com.plucas.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.datasource.fake.FakeBookDataSource;
import com.plucas.graphql.datasource.fake.FakeHelloDataSource;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.SmartSearchResult;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeSmartSearchDataResolver {


    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearch(@InputArgument(name = "keyword")Optional<String> keyword) {
        var smartSearchList = new ArrayList<SmartSearchResult>();

        if (keyword.isEmpty()) {
            smartSearchList.addAll(FakeHelloDataSource.HELLO_LIST);
            smartSearchList.addAll(FakeBookDataSource.BOOK_LIST);
        } else {
            var keywordString = keyword.get();
            FakeHelloDataSource.HELLO_LIST.stream().filter(
                    hello -> StringUtils.containsIgnoreCase(hello.getText(), keywordString)
            ).forEach(smartSearchList::add);

            FakeBookDataSource.BOOK_LIST.stream().filter(
                    book -> StringUtils.containsIgnoreCase(book.getTitle(), keywordString)
            ).forEach(smartSearchList::add);
        }
        return smartSearchList;
    }

}
