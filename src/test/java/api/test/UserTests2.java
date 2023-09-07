package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
    Faker faker;
    User userPayload;

    public Logger logger;

//    this method executes before starting all test methods
    @BeforeClass
    public void setup() {
//        generate data
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

//        logs
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Debugging...");

    }

    @Test(priority = 1)
    public void testPostUser() {

        logger.info("***** Creating User *****");

        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** User Created *****");
    }

    @Test(priority = 2)
    public void testGetUserByName() {

        logger.info("***** Reading User Info *****");

        Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** User Info Displayed *****");
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {

        logger.info("***** Updating User *****");
//        calling these again will regenerate new data!
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** User Info Updated *****");

//        checking data after it was updated
        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {

        logger.info("***** Deleting User *****");

        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***** User Deleted *****");
    }
}
