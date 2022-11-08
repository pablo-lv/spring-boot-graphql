package com.plucas.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.plucas.graphql.datasource.fake.FakeBookDataSource;
import com.plucas.graphql.datasource.fake.FakeMobileAppDataSource;
import com.plucas.graphql.generated.DgsConstants;
import com.plucas.graphql.generated.types.MobileApp;
import com.plucas.graphql.generated.types.MobileAppFilter;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeMobileAppDataResolver {

    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.MobileApps
    )
    public List<MobileApp> getMobileApps(@InputArgument(name = "mobileAppFilter" ) Optional<MobileAppFilter> filter) {
        if (filter.isEmpty()) {
            return FakeMobileAppDataSource.MOBILE_APP_LIST;
        }

        return FakeMobileAppDataSource.MOBILE_APP_LIST.stream().filter(
                mobileApp -> this.matchFilter(filter.get(), mobileApp)
        ).collect(Collectors.toList());
    }

    private boolean matchFilter(MobileAppFilter mobileAppFilter, MobileApp mobileApp) {
        var isAppMatch = StringUtils.containsIgnoreCase(mobileApp.getName() ,
                StringUtils.defaultIfBlank(mobileAppFilter.getName(), StringUtils.EMPTY))
                && StringUtils.containsIgnoreCase(mobileApp.getVersion(),
                StringUtils.defaultIfBlank(mobileAppFilter.getVersion(), StringUtils.EMPTY));

        if (!isAppMatch) {
            return false;
        }

        if (StringUtils.isNotBlank(mobileAppFilter.getPlatform())
        && !mobileApp.getPlatform().contains(mobileAppFilter.getPlatform().toLowerCase())) {
            return false;
        }


        if (mobileAppFilter.getAuthor() != null && !StringUtils.containsIgnoreCase(mobileApp.getAuthor().getName(),
                StringUtils.defaultIfBlank(mobileAppFilter.getAuthor().getName(), StringUtils.EMPTY))) {
            return false;
        }

        return true;
    }
}