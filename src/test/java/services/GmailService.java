package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailService {
    Properties prop;
    public GmailService() throws IOException {
        prop=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
        RestAssured.baseURI=prop.getProperty("GMAIL_BASE_URL");
    }
    public void getGmailList() throws ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
        Response res= given().contentType("application/json")
                .header("Authorization","Bearer " + prop.getProperty("GMAIL_TOKEN"))
                .when().get("/gmail/v1/users/me/messages");
        JsonPath jsonPath=res.jsonPath();
        String messageId= jsonPath.get("messages[0].id").toString();
        Utils.setEnv("messageId",messageId); //store in config file
    }
    public String readLatestEmail() throws IOException, ConfigurationException, org.apache.commons.configuration.ConfigurationException {
        //read gmail first
        getGmailList();

        //reload file
        Properties prop=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);

        //read latest email
        Response res= given().contentType("application/json")
                .header("Authorization","Bearer " + prop.getProperty("GMAIL_TOKEN"))
                .when().get("/gmail/v1/users/me/messages/"+prop.getProperty("messageId"));

        JsonPath jsonPath=res.jsonPath();
        return jsonPath.get("snippet").toString();
    }
}
