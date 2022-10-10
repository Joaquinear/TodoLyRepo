package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static GetProperties intance = null;
    private String host;
    private String user;
    private String pwd;
    private GetProperties(){
        Properties properties = new Properties();
        String nameFile ="todo.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);

        if (inputStream != null){
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        host=properties.getProperty("host");
        user=properties.getProperty("user");
        pwd=properties.getProperty("pwd");

    }
    public static GetProperties getInstance(){
        if(intance == null)
            intance = new GetProperties();
        return intance;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
