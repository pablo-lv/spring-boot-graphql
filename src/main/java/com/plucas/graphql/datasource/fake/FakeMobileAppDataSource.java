package com.plucas.graphql.datasource.fake;

import com.github.javafaker.Faker;
import com.plucas.graphql.generated.types.Address;
import com.plucas.graphql.generated.types.Author;
import com.plucas.graphql.generated.types.MobileApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeMobileAppDataSource {

    public static final List<MobileApp> MOBILE_APP_LIST = new ArrayList<>();

    @Autowired
    private Faker faker;

    @PostConstruct
    private void postConstruct() throws MalformedURLException {
        for (int i = 0; i < 20; i++) {
            var addresses = new ArrayList<Address>();
            var author = Author.newBuilder().addresses(addresses)
                    .name(faker.app().author())
                    .originCountry(faker.country().name())
                    .build();

            var mobile = MobileApp.newBuilder()
                    .name(faker.app().name())
                    .author(author).version(faker.app().version())
                    .platform(randomMobileAppPlatform())
//                    .appId(UUID.randomUUID().toString())
                    .build();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++) {
                var address = Address.newBuilder()
                        .country(faker.address().country())
                        .city(faker.address().cityName())
                        .street(faker.address().streetAddress())
                        .zipCode(faker.address().zipCode())
                        .build();

                addresses.add(address);
            }

            MOBILE_APP_LIST.add(mobile);
        }
    }

    private List<String> randomMobileAppPlatform() {
        switch (ThreadLocalRandom.current().nextInt(10) % 3) {
            case 0:
                return List.of("android");
            case 1:
                return List.of("ios");
            default:
                return List.of("ios", "android");
        }
    }
}
