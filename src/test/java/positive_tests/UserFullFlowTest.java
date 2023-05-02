package positive_tests;

import user.User;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import user.UserCredentials;
import user.UserDefaultMethods;

import static config.TestData.*;
import static org.apache.http.HttpStatus.*;

public class UserFullFlowTest extends UserDefaultMethods {
    private User user;

    @Test
    @Description("In this test is full activity flow under user: create, get info, get token, delete")
    public void ableToCreateGetInfoDeleteUserSuccessful() {
        user = new User(EMAIL_LOW, USERNAME, PASSWORD);
        ValidatableResponse response = create(user);
        Assert.assertEquals(response.extract().statusCode(), SC_CREATED);
        System.out.println("The user "+ response.extract().path("email") + " is created");

        ValidatableResponse responseToken = getToken(new UserCredentials(EMAIL_LOW, PASSWORD));
        Assert.assertEquals(responseToken.extract().statusCode(), SC_OK);
        String token = responseToken.extract().body().path("access").toString();
        System.out.println("The access token is " + token);

        ValidatableResponse responseInfo = getReadMe(token);
        Assert.assertEquals(responseInfo.extract().statusCode(), SC_OK);
        System.out.println("The user info is: " + responseInfo.extract().body().asString());

        ValidatableResponse responseDelete = deleteMe(token, PASSWORD);
        Assert.assertEquals(responseDelete.extract().statusCode(), SC_NO_CONTENT);
        System.out.println("user " + EMAIL_LOW + " is deleted");
    }
}
