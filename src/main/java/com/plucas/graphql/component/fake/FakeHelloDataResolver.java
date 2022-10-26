package com.plucas.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.plucas.graphql.datasource.fake.FakeHelloDataSource;
import com.plucas.graphql.generated.types.Hello;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class FakeHelloDataResolver {

    @DgsQuery
    private List<Hello> allHellos() {
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsQuery
    public Hello oneHello() {
        return FakeHelloDataSource.HELLO_LIST.get(
                ThreadLocalRandom.current().nextInt(FakeHelloDataSource.HELLO_LIST.size())
        );
    }

}
