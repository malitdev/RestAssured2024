package api.test;


import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Initiate Logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("************ Creating User ***************");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();
//        Assert.assertEquals(response.statusCode(),  StatusCodes.SUCCESS_CODE);
        logger.info("************ User Created ***************");
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("************ Reading User Info ***************");
        Response response = UserEndPoints2.readUser(this.userPayload.getUserName());
        response.then().log().all();
//        Assert.assertEquals(response.statusCode(), StatusCodes.NOT_FOUND);
        logger.info("************ User Info Displayed ***************");
    }

    @Test(priority = 3)
    public void testUpdateUserByName() {
        logger.info("************ Updating User ***************");
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());

        Response response = UserEndPoints2.updateUser(userPayload, this.userPayload.getUserName());
        response.then().log().all();
//        Assert.assertEquals(response.statusCode(),  StatusCodes.SUCCESS_CODE);

        //Checking Data after update
        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUserName());
        response.then().log().all();
//        Assert.assertEquals(responseAfterUpdate.statusCode(), StatusCodes.NOT_FOUND);

        logger.info("************ User is Updated ***************");
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){
        logger.info("************ Deleting User ***************");
        Response response = UserEndPoints2.deleteUser(this.userPayload.getUserName());
//        Assert.assertEquals(response.getStatusCode(), StatusCodes.SUCCESS_CODE);
        logger.info("************ User Deleted ***************");
    }

}
