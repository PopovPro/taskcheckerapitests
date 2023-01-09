import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

@Test
public class testClass{
    public void getUserInfo_checkStatusCodeAndMessage_withIncorrectId2() {
        String uid = "incorrectId";
        UserApi.getUserInfo(uid)
                .then()
                .assertThat().statusCode(404)
                .assertThat().body("message", equalTo("User with uid " + uid + " not found"));
    }
}
