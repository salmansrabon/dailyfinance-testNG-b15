package utils;

import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Utils {
    public static int generateRandomNumber(int min, int max){
        double randomNumber= Math.random()*(max-min)+min;
        return  (int) randomNumber;
    }
    public static void saveUserData(JSONObject jsonObject, String jsonFilePath) throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(jsonFilePath));
        jsonArray.add(jsonObject);

        FileWriter fw=new FileWriter(jsonFilePath);
        fw.write(jsonArray.toJSONString());
        fw.flush();
        fw.close();

    }
    public static void getToken(WebDriver driver) throws IOException {
        //wait until the authToken is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')") != null);

        //get the authToken from the localstorage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken');");
        String authTokenData=(String) js.executeScript("return window.localStorage.getItem('authTokenData');");
        System.out.println("Auth Token Retrieved: " + authToken);
        System.out.println("Auth Token Retrieved: " + authTokenData);

        // Save the auth token to a localstorage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken", authToken);
        jsonObject.put("authTokenData", authTokenData);
        FileWriter writer=new FileWriter("./src/test/resources/localstorage.json");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }
    public static void setAuth(WebDriver driver) throws ParseException, InterruptedException, IOException {
        JSONParser jsonParser=new JSONParser();
        JSONObject authObj= (JSONObject) jsonParser.parse(new FileReader( "./src/test/resources/localstorage.json"));
        String authToken= authObj.get("authToken").toString();
        String authTokenData= authObj.get("authTokenData").toString();
        System.out.println(authToken);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", authToken);
        js.executeScript("window.localStorage.setItem('authTokenData', arguments[0]);", authTokenData);
        Thread.sleep(2000);
    }



//    public static void main(String[] args) {
//        System.out.println(generateRandomNumber(1000,9999));
//    }
}
