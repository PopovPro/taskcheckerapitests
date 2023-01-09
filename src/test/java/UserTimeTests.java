import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserTimeTests extends UserApi {
    @Test
    public void getUserTime_checkStatusCodeAndMessage_ForUserHasntStartedTheTest() throws Exception {
        String userUid = createUser();
        getUserTimeResponse(userUid)
            .then()
                .assertThat().statusCode(404)
                .assertThat().body("message", equalTo("User with uid: " + userUid + " has not started quiz yet"));
    }
    @Test
    public void getUserTime_checkStatusCodeAndProperties_ForUserWhoStartedTheTest() throws Exception {
        String userUid = createUser();
        postStart(userUid);
        getUserTimeResponse(userUid)
            .then()
                .assertThat().statusCode(200)
                .assertThat().body("$", hasKey("name"))
                .assertThat().body("$", hasKey("timeLeft"));
    }
    @Test
    public void getUserTime_checkStatusCodeAndProperties_1ForUserWhoStartedTheTest() throws Exception {
        String userUid = createUser();
        postStart(userUid);
        getUserTimeResponse(userUid)
            .then()
                .assertThat().body("timeLeft", allOf(greaterThan(0),lessThan(3001)));
    }

}

