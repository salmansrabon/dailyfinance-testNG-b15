package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.SignupPage;
import utils.Utils;

import java.io.IOException;

public class SignupTestRunner extends Setup {
    @Test(priority = 1, description = "User can signup with all data")
    public void doSignup() throws IOException, ParseException {
        driver.findElement(By.partialLinkText("Register")).click();
        SignupPage signupPage=new SignupPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email="salmansrabon+"+ Utils.generateRandomNumber(1000,9999)+"@gmail.com";
        String password="1234";
        String phoneNumber="0130"+ Utils.generateRandomNumber(10000000,99999999);
        String address=faker.address().fullAddress();

        UserModel userModel=new UserModel();

        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userModel.setAddress(address);
        signupPage.signup(userModel);

        //save user data
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstName",firstName);
        jsonObject.put("lastName",lastName);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phoneNumber",phoneNumber);
        jsonObject.put("address",address);


        Utils.saveUserData(jsonObject,"./src/test/resources/users.json");
    }
    @Test(priority = 2, description = "User can register without optional data")
    public void doSignupWithoutOptional() throws IOException, ParseException {
        SignupPage signupPage=new SignupPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String email="salmansrabon+"+ Utils.generateRandomNumber(1000,9999)+"@gmail.com";
        String password="1234";
        String phoneNumber="0130"+ Utils.generateRandomNumber(10000000,99999999);

        UserModel userModel=new UserModel();

        userModel.setFirstname(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        signupPage.signup(userModel);

        //save user data
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstName",firstName);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phoneNumber",phoneNumber);

        Utils.saveUserData(jsonObject,"./src/test/resources/users.json");
    }
    @AfterMethod
    public void navigateToSignupPage(){
        driver.navigate().to("https://dailyfinance.roadtocareer.net/register");
    }
}
