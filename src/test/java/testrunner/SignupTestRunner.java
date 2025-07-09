package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.SignupPage;
import utils.Utils;

import java.io.IOException;

public class SignupTestRunner extends Setup {
    @Test(priority = 1, description = "User can signup with valid data")
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
        signupPage.signup(firstName,lastName,email,password,phoneNumber,address);

        Utils.saveUserData(firstName,lastName,email,password,phoneNumber,address);
    }
}
