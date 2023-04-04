import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

public class UserCreateNegativeTest extends UserDefaultMethods {
    private User user;
    private String email;
    private String password = "ltym5555";
    private String username = "alina";

    @Test
    @Description("User account is not created with empty email")
    public void accountIsNotCreatedWithEmptyEmailField() {
        user = new User(username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Обязательное поле"));
    }
    @Test
    @Description("User account is not created with email without @")
    public void accountIsNotCreatedWithEmailWithoutSymbol() {
        email = "examplegmail.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }
    @Test
    @Description("User account is not created with several dots in email name part")
    public void accountIsNotCreatedWithDotsEmail() {
        email = "examp..le@gmail.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }
    @Test
    @Description("User account is not created with started with point email ")
    public void accountIsNotCreatedWithStartPointEmail() {
        email = ".example@gmail.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }
    @Test
    @Description("User account is not created with started with point domain ")
    public void accountIsNotCreatedWithStartPointDomain() {
        email = "example@.gmail.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }

}
