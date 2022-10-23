package test;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.ApiConfiguracion;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class PracticeApiItemIntoProject {
    Response response;
    JSONObject body= new JSONObject();
    RequestInfo requestInfo = new RequestInfo();
    @Order(1)
    @DisplayName("Creacion de un projecto y se le agrega items al projecto creado")
    @ParameterizedTest
    @CsvSource(
            {
                    "tarea1JoaquinProjecto,item,2",
                    "tarea2JoaquinProjecto,item,3",
                    "tarea3JoaquinProjecto,item,4",
                    "tarea4JoaquinProjecto,item,5"
            }
    )
    public void addItemIntoProject(String nameProject,String nameItem,int icon){


        body.put("Content",nameProject);
        body.put("Icon",icon);
        requestInfo.setUrl(ApiConfiguracion.CREATE_PROJECT);
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        int idProje = response.then().extract().path("Id");

        for(int i =0; i<=4;i++){
            body.put("Content",nameItem+i);
            body.put("ProjectId",idProje);
            requestInfo.setUrl(String.format(ApiConfiguracion.CREATE_ITEM_PROJECT,idProje));
            requestInfo.setBody(body.toString());
            response = FactoryRequest.make("post").send(requestInfo);
            response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
            int idItem = response.then().extract().path("Id");

            body.put("Content","updateTarea "+idItem+"");
            requestInfo.setUrl(String.format(ApiConfiguracion.UPDATE_ITEM,idItem));
            requestInfo.setBody(body.toString());
            response = FactoryRequest.make("put").send(requestInfo);
            response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

            requestInfo.setUrl(String.format(ApiConfiguracion.DELETE_ITEM,idItem));
            response = FactoryRequest.make("delete").send(requestInfo);
            response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        }
        requestInfo.setUrl(String.format(ApiConfiguracion.GET_ALL_ITEM_BY_PROJECT,idProje));
        response = FactoryRequest.make("get").send(requestInfo);
        response.then().body("Info.ItemsCount",equalTo(0));

        requestInfo.setUrl(String.format(ApiConfiguracion.DELETE_PROJECT,idProje));
        requestInfo.setBody(body.toString());
        response= FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Id",equalTo(body.get("ProjectId"))).statusCode(200);
    }
}
