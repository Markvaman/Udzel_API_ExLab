import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static config.TestData.*;
import static org.apache.http.HttpStatus.SC_CREATED;

public class PasswordUpdateTests extends UserDefaultMethods {
    private User user;
    private UserCredentials userCredentials;

    @BeforeMethod
    public void userCreate() {
        user = new User(EMAIL_LOW, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The current email is " + EMAIL_LOW);
        userCredentials = new UserCredentials(EMAIL_LOW, PASSWORD);
    }

    @Test
    @Description("The password update with lower case is successfully")
    public void passwordUpdateWithLowerCase(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updatePassword(accessToken, PASSWORD_LOW);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        Assert.assertEquals(response.extract().body().path("new_password").toString(), PASSWORD_LOW);
        System.out.println("The password is updated to " + PASSWORD_LOW);

        deleteUser(EMAIL_LOW, PASSWORD_LOW);
    }
    @Test
    @Description("The password update with upper case is successfully")
    public void passwordUpdateWithUpperCase(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updatePassword(accessToken, PASSWORD_UP);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        Assert.assertEquals(response.extract().body().path("new_password").toString(), PASSWORD_UP);
        System.out.println("The password is updated to " + PASSWORD_UP);

        deleteUser(EMAIL_LOW, PASSWORD_UP);
    }
    @Test
    @Description("The password update with upper and lower cases is successfully")
    public void passwordUpdateWithBothCases(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updatePassword(accessToken, PASSWORD_BOTH);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        Assert.assertEquals(response.extract().body().path("new_password").toString(), PASSWORD_BOTH);
        System.out.println("The password is updated to " + PASSWORD_BOTH);

        deleteUser(EMAIL_LOW, PASSWORD_BOTH);
    }
    @Test
    @Description("The password update with numbers and letters is successfully")
    public void passwordUpdateWithNumbers(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updatePassword(accessToken, PASSWORD_DIGITS);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        Assert.assertEquals(response.extract().body().path("new_password").toString(), PASSWORD_DIGITS);
        System.out.println("The password is updated to " + PASSWORD_DIGITS);

        deleteUser(EMAIL_LOW, PASSWORD_DIGITS);
    }
    @Test
    @Description("The password update with upper case is successfully")
    public void passwordUpdateWithSymbols(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        ValidatableResponse response = updatePassword(accessToken, PASSWORD_SYMBOLS);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        Assert.assertEquals(response.extract().body().path("new_password").toString(), PASSWORD_SYMBOLS);
        System.out.println("The password is updated to " + PASSWORD_SYMBOLS);

        deleteUser(EMAIL_LOW, PASSWORD_SYMBOLS);
    }

}
