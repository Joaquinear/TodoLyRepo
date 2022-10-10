package test;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.ApiConfiguracion;

import static org.hamcrest.Matchers.equalTo;

public class PracticeApisTodo {
    Response response;
    JSONObject body= new JSONObject();
    RequestInfo requestInfo = new RequestInfo();
    @ParameterizedTest
    @CsvSource(
            {
                    "tarea1Joaquin",
                    "tarea2Joaquin",
                    "tarea3Joaquin",
                    "tarea4Joaquin"
            }
    )
    public void projectCRUDItems(String nameItem){
        body.put("Content",nameItem);
        //create item
        requestInfo.setUrl(ApiConfiguracion.CREATE_ITEM);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        int idDelItem = response.then().extract().path("Id");
        //All items
        requestInfo.setUrl(ApiConfiguracion.READ_ITEM_ALL);
        response = FactoryRequest.make("get").send(requestInfo);
        response.then().statusCode(200);
        //Item by ID
        requestInfo.setUrl(String.format(ApiConfiguracion.READ_ITEM_BY_ID,idDelItem));
        response = FactoryRequest.make("get").send(requestInfo);
        response .then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        //update Item
        body.put("Content","updateTarea "+idDelItem+"");
        requestInfo.setUrl(String.format(ApiConfiguracion.UPDATE_ITEM,idDelItem));
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("put").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        //Delete one to one the elements that its enter on the items list
        requestInfo.setUrl(String.format(ApiConfiguracion.DELETE_ITEM,idDelItem));
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        requestInfo.setUrl(String.format(ApiConfiguracion.DELETE_PROJECT,idDelItem));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
    }

}
