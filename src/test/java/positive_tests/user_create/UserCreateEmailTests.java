package positive_tests.user_create;

import user.User;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import user.UserDefaultMethods;

import static config.TestData.*;
import static org.apache.http.HttpStatus.SC_CREATED;

public class UserCreateEmailTests extends UserDefaultMethods {
    private User user;

    @Test
    @Description("User.User account is created successfully with lower case email")
    public void accountIsCreatedWithLowerCaseEmail(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

        deleteUser(EMAIL_LOW, PASSWORD);

    }
    @Test
    @Description("User.User account is created successfully with lower case email")
    public void accountIsCreatedWithUppercaseEmail() {
        user = new User(EMAIL_UP, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

        EMAIL_UP = EMAIL_UP.toLowerCase();
        deleteUser(EMAIL_UP, PASSWORD);
       }

    @Test
    @Description("User.User account is created successfully with number starting email")
    public void accountIsCreatedWithNumberStartingEmail() {
        user = new User(EMAIL_No, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

        deleteUser(EMAIL_No, PASSWORD);
    }
    @Test
    @Description("User.User account is created successfully with dots in the name part of email")
    public void accountIsCreatedWithDotsInNameEmail() {
        user = new User(EMAIL_DOT, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

        deleteUser(EMAIL_DOT, PASSWORD);
    }
    @Test
    @Description("User.User account is created successfully with dots in the domain part of email")
    public void accountIsCreatedWithDotsInDomainEmail() {
        user = new User(EMAIL_DOM_DOT, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

        deleteUser(EMAIL_DOM_DOT, PASSWORD);
    }
    @Test
    @Description("User.User account is created successfully with space in email")
    public void accountIsCreatedWithSpaceEmail() {
        user = new User(EMAIL_SPACE, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals(statusCode, SC_CREATED);

        String userEmail = response.extract().path("email");
        System.out.println("The user "+ userEmail + " is created");

        deleteUser(EMAIL_SPACE, PASSWORD);
    }

    }

