import io.restassured.RestAssured;

import java.io.IOException;
import java.util.Properties;

public class ApiTestBase {
    public ApiTestBase() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            RestAssured.baseURI = prop.getProperty("api.uri");
            RestAssured.port = Integer.parseInt(prop.getProperty("api.port"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
