import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class QuestionControllerTests extends ApiTestBase {
    @Test
    public void getUserInfo_checkStatusCode_expect200() {
        given()
                .cookies(getAuthCookies())
             .when()
                .get("/api/public/questions/allSecure")
             .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", hasSize(greaterThan(0)));
    }
}
