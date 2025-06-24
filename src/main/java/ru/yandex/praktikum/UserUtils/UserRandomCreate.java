package ru.yandex.praktikum.UserUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserRandomCreate {
    private User user;

@Step("Method for user create with valid date")
    public User createNewUser() {
        user = new User(
                RandomStringUtils.randomAlphanumeric(5),
                RandomStringUtils.randomAlphanumeric(5) + "@" + RandomStringUtils.randomAlphanumeric(4) + ".ru",
                RandomStringUtils.randomAlphanumeric(7)
        );
        return user;
    }

@Description("The minimum password is six characters")
@Step("Method for user create with short password")
    public User createNewUserWithShortPassword(){
        user = new User(
                RandomStringUtils.randomAlphanumeric(5),
                RandomStringUtils.randomAlphanumeric(5) + "@" + RandomStringUtils.randomAlphanumeric(4) + ".ru",
                RandomStringUtils.randomAlphanumeric(5)
        );
        return user;
    }
}