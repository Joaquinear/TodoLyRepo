package utils;

public class ApiConfiguracion {
    public  static final String CREATE_ITEM=GetProperties.getInstance().getHost()+"api/Items.json";
    public  static final String UPDATE_ITEM=GetProperties.getInstance().getHost()+"api/Items/%s.json";
    public  static final String DELETE_ITEM=GetProperties.getInstance().getHost()+"api/Items/%s.json";
    public  static final String READ_ITEM_ALL =GetProperties.getInstance().getHost()+"api/Items.json";
    public  static final String READ_ITEM_BY_ID=GetProperties.getInstance().getHost()+"api/Items/%s.json";


    public  static final String CREATE_PROJECT=GetProperties.getInstance().getHost()+"api/projects.json";
    public  static final String CREATE_ITEM_PROJECT=GetProperties.getInstance().getHost()+"api/Items.json";
    public  static final String DELETE_PROJECT=GetProperties.getInstance().getHost()+"api/projects/%s.json";
    public static final String GET_ALL_ITEM_BY_PROJECT = GetProperties.getInstance().getHost()+"api/Projects/%s/ItemsWithInfo.json";

}
