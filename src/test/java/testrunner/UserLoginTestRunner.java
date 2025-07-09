package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserLoginTestRunner extends Setup {
    @Test(priority = 1, description = "User can login with valid creds")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=jsonObject.get("email").toString();
        String password=jsonObject.get("password").toString();
        loginPage.userLogin(email,password);
    }
}
