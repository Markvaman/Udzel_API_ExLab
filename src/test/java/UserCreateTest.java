import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.apache.http.HttpStatus.*;

import java.util.Locale;

public class UserCreateTest extends UserDefaultMethods {
    private User user;
    private String accessToken;
    private String uid;
    private Faker faker = new Faker();
    private String email1 = faker.internet().emailAddress().toLowerCase();
    private String email2 = faker.internet().emailAddress().toUpperCase();
    private String password = "ltym5555";
    private String username = "alina";

    @AfterMethod
    public void deleteUser() {
        deleteMe();
    }

    @Test
    @Description("User account is created successfully with lower case email")
    public void accountIsCreatedWithLowerCaseEmail(){
        user = new User(email1, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
    }
    @Test
    @Description("User account is created successfully with lower case email")
    public void accountIsCreatedWithUppercaseEmail() {
        user = new User(email2, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
    }

}
