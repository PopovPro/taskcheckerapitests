import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserControllerTests extends ApiTestBase {
    @Test
    public void getUserInfo_checkStatusCode_expect200() {
        UserApi.getUserInfo("a33218c3-f3c0-46c9-ae69-af23c7fb50a6")
            .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void getUserInfo_checkStatusCodeAndMessage_withIncorrectId() {
        String uid = "incorrectId";
        UserApi.getUserInfo(uid)
            .then()
                .assertThat().statusCode(404)
                .assertThat().body("message", equalTo("User with uid " + uid + " not found"));
    }
}
