package negative_tests.create_tests;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import user.User;
import user.UserDefaultMethods;

import static config.TestData.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

public class UserCreateUsernameNegativeTests extends UserDefaultMethods {
    private User user;

    @Test
    @Description("User account is not created with empty username")
    public void accountIsNotCreatedWithEmptyUsername() {
        user = new User(EMAIL_LOW, USERNAME_EMPTY, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("username").toString().contains("Это поле не может быть пустым."));
    }
    @Test
    @Description("User account is not created with empty email")
    public void accountIsNotCreatedWithTooLongUsername() {
        user = new User(EMAIL_EMPTY, USERNAME_LONG, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("username").toString().contains("Это поле не может быть пустым."));
    }


}
