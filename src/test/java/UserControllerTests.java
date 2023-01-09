import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import Models.UserInfo;
import com.google.gson.Gson;
import org.testng.Assert;
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

    @Test
    public void getUserInfo_returnCorrectUid() throws Exception {
        String userUid = UserApi.createUser();
        var jsonSting = UserApi.getUserInfo(userUid).getBody().asString();
        var user = new Gson().fromJson(jsonSting, UserInfo.class);
        Assert.assertEquals(user.uid, userUid);
    }

    @Test
    public void getUserInfo_checkPropertiesForUserWhoStartedTheTest() throws Exception {
        String userUid = UserApi.createUser();
        UserApi.postStart(userUid);
        UserApi.getUserInfo(userUid)
                .then().log().all()
                .assertThat().body("$", hasKey("name"))
                .assertThat().body("$", hasKey("uid"))
                .assertThat().body("$", hasKey("startTime"))
                .assertThat().body("$", hasKey("endTime"))
                .assertThat().body("$", hasKey("isStarted"));
    }
}
