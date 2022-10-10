package factoryRequest;

public class FactoryRequest {
    public static IRequest make(String requestType){
        IRequest request;
        switch (requestType.toLowerCase()){
            case"post":
                request = new RequestPOST();
                break;
            case"get":
                request = new RequestGET();
                break;
            case"delete":
                request = new RequestDELETE();
                break;
            case"put":
                request = new RequestPUT();
                break;
            default:
                request = new RequestGET();
                break;
        }
        return request;
    }
}
