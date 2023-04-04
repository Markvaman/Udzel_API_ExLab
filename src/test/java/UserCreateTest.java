import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_CREATED;

public class UserCreateTest extends UserDefaultMethods {
    private User user;
    private String accessToken;
    private String uid;
    private Faker faker = new Faker();
    private String password = "ltym5555";
    private String username = "alina";
    private String email;

    @AfterMethod
    public void deleteUser() {
        UserCredentials userCredentials = new UserCredentials(email, password);
        accessToken = getToken(userCredentials).extract().body().path("access").toString();
        deleteMe(accessToken, password);
    }

    @Test
    @Description("User account is created successfully with lower case email")
    public void accountIsCreatedWithLowerCaseEmail(){
        email = faker.internet().emailAddress().toLowerCase();
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

    }
    @Test
    @Description("User account is created successfully with lower case email")
    public void accountIsCreatedWithUppercaseEmail() {
        email = faker.internet().emailAddress().toUpperCase();
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
       }

    @Test
    @Description("User account is created successfully with number starting email")
    public void accountIsCreatedWithNumberStartingEmail() {
        email = "4example@gmail.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
    }
    @Test
    @Description("User account is created successfully with dots in the name part of email")
    public void accountIsCreatedWithDotsInNameEmail() {
        email = "ex.e.example@gmail.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
    }
    @Test
    @Description("User account is created successfully with dots in the domain part of email")
    public void accountIsCreatedWithDotsInDomainEmail() {
        email = "example@exlab.l.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
    }
    @Test
    @Description("User account is created successfully with space in email")
    public void accountIsCreatedWithSpaceEmail() {
        email = "example-exam@exlab.com";
        user = new User(email, username, password);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");
    }

    }

