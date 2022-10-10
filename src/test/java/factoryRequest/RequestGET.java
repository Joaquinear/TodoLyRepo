package factoryRequest;

import io.restassured.response.Response;
import utils.GetProperties;

import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest {
    @Override
    public Response send(RequestInfo info) {
        Response response =given()
                .auth()
                .preemptive()
                .basic(GetProperties.getInstance().getUser(),
                        GetProperties.getInstance().getPwd())
                .body(info.getBody())
                .log().all()
                .when()
                .get(info.getUrl());
        response.then().log().all();
        return response;
    }
}