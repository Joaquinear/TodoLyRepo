package test;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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
    @Order(2)
    @DisplayName("Creacion de usuarios y lectura")
    @Description("este test verifica la creacion de usuarios y lectura de la creacion con la pagina todoLY")
    @ParameterizedTest
    //AQUI SE TIENE QUE CAMBIAR EL CORREO CADA QUE SE EJECUTA PARA QUE NO DE ERROR
    @CsvSource(
            {
                    "pruebas56@usermail.com,useremail,password",
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
    @Order(3)
    @DisplayName("Actualizacion y eliminado de cuenta todoly")
    @Test
    public void UpdateAndDelete(){
        body.put("Email","Joaquinear21@gmail.com");
        requestInfo.setUrl(ApiConfigurationUser.UPDATE_USER);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Email",equalTo(body.get("Email"))).statusCode(200);


        requestInfo.setUrl(ApiConfigurationUser.DELETE_USER);
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Email",equalTo(body.get("Email"))).statusCode(200);
    }

}
