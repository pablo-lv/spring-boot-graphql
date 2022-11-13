package com.plucas.graphql.datasource.fake;

import com.github.javafaker.Faker;
import com.netflix.graphql.dgs.DgsComponent;
import com.plucas.graphql.generated.types.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

@Configuration
public class FakeStockDataSource {

    @Autowired
    private Faker faker;

    public Stock randomStock() {
        return Stock.newBuilder().lastTradeDateTime(OffsetDateTime.now())
                .price(faker.random().nextInt(100, 1000))
                .symbol(faker.stock().nyseSymbol())
                .build();
    }

}
