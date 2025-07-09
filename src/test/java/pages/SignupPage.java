package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignupPage {
    @FindBy(id="firstName")
    WebElement txtFirstName;
    @FindBy(id="lastName")
    WebElement txtLastName;
    @FindBy(id="email")
    WebElement txtEmail;
    @FindBy(id="password")
    WebElement txtPassword;
    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy(id="address")
    WebElement txtAddress;
    @FindBy(css = "[type=radio]")
    List<WebElement> rbGender;
    @FindBy(css = "[type=checkbox]")
    WebElement chkTerms;
    @FindBy(id="register")
    WebElement btnRegister;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void signup(String firstName, String lastName, String email, String password, String phoneNumber, String address){
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtPhoneNumber.sendKeys(phoneNumber);
        txtAddress.sendKeys(address);
        rbGender.get(0).click();
        chkTerms.click();
        btnRegister.click();
    }

}
