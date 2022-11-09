package com.plucas.graphql.datasource.fake;

import com.github.javafaker.Faker;
import com.plucas.graphql.generated.types.Cat;
import com.plucas.graphql.generated.types.Dog;
import com.plucas.graphql.generated.types.Pet;
import com.plucas.graphql.generated.types.PetFoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakePetDataSource {

    public static final List<Pet> PET_LIST = new ArrayList<>();

    @Autowired
    private Faker faker;


    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 20; i++) {
            Pet animal = buildRandomPet(i);
            PET_LIST.add(animal);
        }
    }

    private Pet buildRandomPet(int index) {
        if (index % 2 == 0) {
            return Dog.newBuilder().name(faker.dog().name())
                    .food(PetFoodType.OMNIVORE)
                    .breed(faker.dog().breed()).size(faker.dog().size()).coatLength(faker.dog().coatLength()).build();
        }
        return Cat.newBuilder().name(faker.cat().name())
                .food(PetFoodType.OMNIVORE)
                .breed(faker.cat().breed()).registry(faker.cat().registry()).build();
    }
}
