package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    private static final String BASE_URL = "http://udzel.hopto.org/";
    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(BASE_URL).build();
    }
}
