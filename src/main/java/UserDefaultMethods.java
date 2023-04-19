import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class UserDefaultMethods extends RestClient {
    @Step("Create new user")
    public ValidatableResponse create(User user) {
        return
                given()
                        .spec(getBaseSpec())
                        .body(user)
                        .post("/api/users/")
                        .then();
    }

    @Step("Get token")
    public ValidatableResponse getToken(UserCredentials userCredentials) {
        return
                given()
                        .spec(getBaseSpec())
                        .body(userCredentials)
                        .when()
                        .post("/api/jwt/create/")
                        .then();
    }


    @Step("Self-delete")
    public ValidatableResponse deleteMe(String accessToken, String password){
        String body = "{\n" +
                "    \"current_password\" : \"" + password + "\"\n" +
                "}";
        return
                given()
                        .spec(getBaseSpec())
                        .header("Authorization", "Token " + accessToken)
                        .body(body)
                        .when()
                        .delete("/api/users/me/")
                        .then();
    }

    @Step("Get user list")
    public ValidatableResponse getUsersList(){
        return
                given()
                        .spec(getBaseSpec())
                        .when()
                        .get("/api/users/")
                        .then();
    }

    @Step("Get my information")
    public ValidatableResponse getReadMe(String accessToken){
        return
                given()
                        .spec(getBaseSpec())
                        .header("Authorization", "Token " + accessToken)
                        .when()
                        .get("/api/users/me/")
                        .then();
    }

    @Step("Update username successfully")
    public ValidatableResponse updateUsername(String accessToken, User user){
        return
                given()
                        .spec(getBaseSpec())
                        .header("Authorization", "Token " + accessToken)
                        .body(user)
                        .when()
                        .patch("/api/users/me/")
                        .then();

    }

    @Step("Full delete flaw")
    public void deleteUser(String email, String password){
        String accessToken = getToken(new UserCredentials(email, password)).extract().body().path("access").toString();
        deleteMe(accessToken, password);
    }

//    public void fullGetToken(UserCredentials userCredentials){
//        ValidatableResponse responseToken = getToken(userCredentials);
//        String accessToken = responseToken.extract().body().path("access").toString();
//        Assert.assertNotNull(accessToken);
//    }

}
