package positive_tests.user_update;

import user.User;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import user.UserCredentials;
import user.UserDefaultMethods;

import static config.TestData.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class EmailUpdateTests extends UserDefaultMethods {
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
    @Description("The email update with upper case is successfully")
    public void emailUpdateWithUpperCase(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        user = new User(EMAIL_UP, USERNAME);
        ValidatableResponse response = updateEmail(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("email").toString(), EMAIL_UP);
        System.out.println("The email is updated to " + EMAIL_UP);

        deleteUser(EMAIL_UP.toLowerCase(), PASSWORD);
    }
    @Test
    @Description("The email update start with number is successfully")
    public void emailUpdateWithNumber(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        user = new User(EMAIL_No, PASSWORD);
        ValidatableResponse response = updateEmail(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("email").toString(), EMAIL_No);
        System.out.println("The email is updated to " + EMAIL_No);

        deleteUser(EMAIL_No, PASSWORD);
    }
    @Test
    @Description("The email update with dot in name part is successfully")
    public void emailUpdateWithDot(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        user = new User (EMAIL_DOT, PASSWORD);
        ValidatableResponse response = updateEmail(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("email").toString(), EMAIL_DOT);
        System.out.println("The email is updated to " + EMAIL_DOT);

        deleteUser(EMAIL_DOT, PASSWORD);
    }
    @Test
    @Description("The email update with dot in domain part is successfully")
    public void emailUpdateWithDotDomain(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        user = new User(EMAIL_DOMAIN_DOT, PASSWORD);
        ValidatableResponse response = updateEmail(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("email").toString(), EMAIL_DOMAIN_DOT);
        System.out.println("The email is updated to " + EMAIL_DOMAIN_DOT);

        deleteUser(EMAIL_DOMAIN_DOT, PASSWORD);
    }
    @Test
    @Description("The email update with dash is successfully")
    public void emailUpdateWithDash(){
        ValidatableResponse responseToken = getToken(userCredentials);
        String accessToken = responseToken.extract().body().path("access").toString();
        Assert.assertNotNull(accessToken);

        user = new User (EMAIL_SPACE, PASSWORD);
        ValidatableResponse response = updateEmail(accessToken, user);
        Assert.assertEquals(response.extract().statusCode(), SC_OK);
        Assert.assertEquals(response.extract().body().path("email").toString(), EMAIL_SPACE);
        System.out.println("The email is updated to " + EMAIL_SPACE);

        deleteUser(EMAIL_SPACE, PASSWORD);
    }

}
