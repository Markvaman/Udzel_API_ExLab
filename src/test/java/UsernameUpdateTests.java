import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static config.TestData.*;
import static org.apache.http.HttpStatus.*;

public class UsernameUpdateTests extends UserDefaultMethods {
    private User user;
    private UserCredentials userCredentials;

    @BeforeMethod
    public void userCreate(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        userCredentials = new UserCredentials(EMAIL_LOW, PASSWORD);
//        ValidatableResponse responseToken = getToken(userCredentials);
//        String accessToken = responseToken.extract().body().path("access").toString();
//        Assert.assertNotNull(accessToken);
    }

    @Test
    @Description("The username update with upper and lower cases name is successfully")
    public void usernameIsUpdateWithUpperAndLowerCase(){
        USERNAME = USERNAME_UP_LOW;
        user = new User(EMAIL_LOW, USERNAME_UP_LOW);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_UP_LOW);
        System.out.println("The username is updated to " + USERNAME_UP_LOW);

        deleteMe(accessToken, PASSWORD);
    }
    @Test
    @Description("The username is update with cyrillic letters")
    public void usernameIsUpdateWithCyrillicLetters(){
        USERNAME = USERNAME_CYRILLIC;
        user = new User(EMAIL_LOW, USERNAME_CYRILLIC);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_CYRILLIC);
        System.out.println("The username is updated to " + USERNAME_CYRILLIC);

        deleteMe(accessToken, PASSWORD);
    }
    @Test
    @Description("The username is update with digits in name")
    public void usernameIsUpdateWithDigits(){
        USERNAME = USERNAME_DIGITS;
        user = new User(EMAIL_LOW, USERNAME_DIGITS);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_DIGITS);
        System.out.println("The username is updated to " + USERNAME_DIGITS);

        deleteMe(accessToken, PASSWORD);
    }
    @Test
    @Description("The username is update with special symbols @ + - _")
    public void usernameIsUpdateWithSymbols(){
        USERNAME = USERNAME_SYMBOL;
        user = new User(EMAIL_LOW, USERNAME_SYMBOL);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_SYMBOL);
        System.out.println("The username is updated to " + USERNAME_SYMBOL);

        deleteMe(accessToken, PASSWORD);
    }
    @Test
    @Description("The username is update equaled to email")
    public void usernameIsUpdateEqualedToEmail(){
        USERNAME = EMAIL_LOW;
        user = new User(EMAIL_LOW, USERNAME);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME);
        System.out.println("The username is updated to " + USERNAME);

        deleteMe(accessToken, PASSWORD);
    }

}
