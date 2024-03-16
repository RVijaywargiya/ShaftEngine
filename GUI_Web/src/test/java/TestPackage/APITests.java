package TestPackage;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APITests {

    SHAFT.API api;

    @Test
    public void test_get() {
        api = new SHAFT.API("https://jsonplaceholder.typicode.com");
        api.get("/users").perform();
        api.assertThatResponse().extractedJsonValue("$[?(@.name=='Chelsey Dietrich')].id").isEqualTo("5").perform();
    }

    @Test
    public void test_post() {
        api = new SHAFT.API("https://reqres.in/");
        String body = """
                {
                    "name": "morpheus",
                    "job": "leader"
                }""";
        api.post("api/users").setRequestBody(body).setTargetStatusCode(201).setContentType(ContentType.JSON).perform();
        api.assertThatResponse().extractedJsonValue("name").isEqualTo("morpheus").withCustomReportMessage("Check that Morpheus exists.").perform();
    }

    @Test
    public void createItemsCart() {
        api = new SHAFT.API("https://simple-grocery-store-api.glitch.me");
        api.post("/carts").perform();
        System.out.println(api.getResponseBody());
        System.out.println(api.getResponseStatusCode());
    }
}
