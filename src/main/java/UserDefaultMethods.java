import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

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
    public ValidatableResponse getToken(UserCredentials credentials) {
        return
                given()
                        .spec(getBaseSpec())
                        .body(credentials)
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
    public ValidatableResponse getReadMe(){
        return
                given()
                        .spec(getBaseSpec())
                        .when()
                        .get("/api/users/me/")
                        .then();
    }

}
