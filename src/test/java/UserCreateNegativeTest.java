import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import static config.TestData.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

public class UserCreateNegativeTest extends UserDefaultMethods {
    private User user;

    @Test
    @Description("User account is not created with empty email")
    public void accountIsNotCreatedWithEmptyEmailField() {
        user = new User(EMAIL_EMPTY, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Это поле не может быть пустым."));
    }
    @Test
    @Description("User account is not created with email without @")
    public void accountIsNotCreatedWithEmailWithoutSymbol() {
        user = new User(EMAIL_WITHOUT_AT, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }
    @Test
    @Description("User account is not created with several dots in email name part")
    public void accountIsNotCreatedWithDotsEmail() {
        user = new User(EMAIL_WITH_DOTS_ROW, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }
    @Test
    @Description("User account is not created with started with dot email ")
    public void accountIsNotCreatedWithStartPointEmail() {
        user = new User(EMAIL_FIRST_DOT, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }
    @Test
    @Description("User account is not created with started with dot domain ")
    public void accountIsNotCreatedWithStartPointDomain() {
        user = new User(EMAIL_DOMAIN_DOT, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_BAD_REQUEST);
        Assert.assertTrue(response.extract().path("email").toString().contains("Введите правильный адрес электронной почты"));
    }

}
