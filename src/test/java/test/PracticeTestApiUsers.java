package test;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.ApiConfigurationUser;
import utils.GetProperties;

import static org.hamcrest.Matchers.equalTo;

public class PracticeTestApiUsers {
    Response response;
    JSONObject body= new JSONObject();
    RequestInfo requestInfo = new RequestInfo();
    @ParameterizedTest
    @CsvSource(
            {
                    "pruebas19@usermail.com,useremail,password",
            }
    )
    public void testUser(String email,String user,String pwd){
        //create
        body.put("Email",email);
        body.put("FullName",user);
        body.put("Password",pwd);
        requestInfo.setUrl(ApiConfigurationUser.CREATE_USER);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Email",equalTo(body.get("Email"))).statusCode(200);

        requestInfo.setUrl(ApiConfigurationUser.READ_USER);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then().body("Email",equalTo(GetProperties.getInstance().getUser())).statusCode(200);
    }
    @Test
    public void UpdateAndDelete(){
        body.put("Email","joaquinear21@gmail.com");
        requestInfo.setUrl(ApiConfigurationUser.UPDATE_USER);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Email",equalTo(body.get("Email"))).statusCode(200);


        requestInfo.setUrl(ApiConfigurationUser.DELETE_USER);
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Email",equalTo(body.get("Email"))).statusCode(200);
    }

}
