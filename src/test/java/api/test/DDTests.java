package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilites.DataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DDTests {

    //1. Post user
    @Test(priority = 1, dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname,String lname, String useremail, String pwd, String ph) {
       User userPayload =  new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUserName(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayload);
//        Assert.assertEquals(response.statusCode(),  StatusCodes.SUCCESS_CODE);
    }

    //2. Delete User by name
    @Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName) {

        Response response = UserEndPoints.deleteUser(userName);
//        Assert.assertEquals(response.statusCode(),  StatusCodes.SUCCESS_CODE);
    }


}
