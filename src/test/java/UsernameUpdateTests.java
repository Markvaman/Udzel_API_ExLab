import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static config.TestData.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class UsernameUpdateTests extends UserDefaultMethods {
    private User user;
    private UserCredentials userCredentials;

    @BeforeMethod
    public void userCreate(){
        user = new User(EMAIL_LOW, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        userCredentials = new UserCredentials(EMAIL_LOW, PASSWORD);
        System.out.println("The current username is " + USERNAME);
    }
    @AfterMethod
    public void deleteUser(){
        deleteUser(EMAIL_LOW, PASSWORD);
    }

    @Test
    @Description("The username update with upper and lower cases name is successfully")
    public void usernameIsUpdateWithUpperAndLowerCase(){
        user = new User(EMAIL_LOW, USERNAME_UP_LOW);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, USERNAME_UP_LOW);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_UP_LOW);
        System.out.println("The username is updated to " + USERNAME_UP_LOW);
    }
    @Test
    @Description("The username is update with cyrillic letters")
    public void usernameIsUpdateWithCyrillicLetters(){
        user = new User(EMAIL_LOW, USERNAME_CYRILLIC);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, USERNAME_CYRILLIC);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_CYRILLIC);
        System.out.println("The username is updated to " + USERNAME_CYRILLIC);
    }
    @Test
    @Description("The username is update with digits in name")
    public void usernameIsUpdateWithDigits(){
        user = new User(EMAIL_LOW, USERNAME_DIGITS);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, USERNAME_DIGITS);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_DIGITS);
        System.out.println("The username is updated to " + USERNAME_DIGITS);

    }
    @Test
    @Description("The username is update with special symbols @ + - _")
    public void usernameIsUpdateWithSymbols(){
        user = new User(EMAIL_LOW, USERNAME_SYMBOL);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, USERNAME_SYMBOL);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), USERNAME_SYMBOL);
        System.out.println("The username is updated to " + USERNAME_SYMBOL);
    }
    @Test
    @Description("The username is update equaled to email")
    public void usernameIsUpdateEqualedToEmail(){
        user = new User(EMAIL_LOW, EMAIL_LOW);
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updateUsername(accessToken, EMAIL_LOW);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("username").toString(), EMAIL_LOW);
        System.out.println("The username is updated to " + EMAIL_LOW);
    }

}
