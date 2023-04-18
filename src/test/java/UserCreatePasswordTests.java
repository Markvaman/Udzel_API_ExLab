import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static config.TestData.*;
import static org.apache.http.HttpStatus.*;

public class UserCreatePasswordTests extends UserDefaultMethods {
    private User user;
    private UserCredentials userCredentials;

    @Test
    @Description("Lower case password is accepted")
    public void userIsCreatedWithLowercasePassword(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD_LOW);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The user with password " + PASSWORD_LOW + " is created");

        deleteUser(EMAIL_LOW, PASSWORD_LOW);
    }
    @Test
    @Description("Upper case password is accepted")
    public void userIsCreatedWithUpperCasePassword(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD_UP);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The user with password " + PASSWORD_UP + " is created");

        deleteUser(EMAIL_LOW, PASSWORD_UP);
    }
    @Test
    @Description("Upper and lower cases password is accepted")
    public void userIsCreatedWithLowerAndUpperCasePassword(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD_BOTH);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The user with password " + PASSWORD_BOTH + " is created");

        deleteUser(EMAIL_LOW, PASSWORD_BOTH);
    }
    @Test
    @Description("Letters and digits password is accepted")
    public void userIsCreatedWithLettersAndDigitsPassword(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD_DIGITS);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The user with password " + PASSWORD_DIGITS + " is created");

        deleteUser(EMAIL_LOW, PASSWORD_DIGITS);
    }
    @Test
    @Description("Symbols password is accepted")
    public void userIsCreatedWithSymbolsInPassword(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD_SYMBOLS);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The user with password " + PASSWORD_SYMBOLS + " is created");

        deleteUser(EMAIL_LOW, PASSWORD_SYMBOLS);
    }
}
