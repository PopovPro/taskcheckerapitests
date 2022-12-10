import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    public static Response getUserInfo(String o) {
        return given()
                .pathParam("uid", o)
                .when()
                .get("/api/public/user/info/{uid}");
    }
}
