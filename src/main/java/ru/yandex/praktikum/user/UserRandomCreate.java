package ru.yandex.praktikum.user;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import net.datafaker.Faker;

public class UserRandomCreate {
    private User user;
    private static final Faker FAKER = new Faker();

@Step("Method for user create with valid date")
    public User createNewUser() {
         return new User(
            FAKER.name().firstName(),
            FAKER.internet().emailAddress(),
            FAKER.internet().password()
         );
    }

@Description("The minimum password is six characters")
@Step("Method for user create with short password")
    public User createNewUserWithShortPassword(){

          return new User(
            FAKER.name().firstName(),
            FAKER.internet().emailAddress(),
            FAKER.internet().password(5, 5, true, true, true)
         );
    }
}