import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class UserControllerTests extends ApiTestBase {
    @Test
    public void getUserInfo_checkStatusCode_expect200() throws Exception {
        String userUid = UserApi.createUser();
        UserApi.getUserInfo(userUid)
            .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void getUserInfo_checkBodyHasNameAndUid() throws Exception {
        String userUid = UserApi.createUser();
        UserApi.getUserInfo(userUid)
                .then().log().all()
                .assertThat().body("$", hasKey("name"))
                .assertThat().body("$", hasKey("uid"));
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
