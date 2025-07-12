package testrunner;

import config.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class AdminLoginTestRunner extends Setup {
    @Test(priority = 1, description = "Admin tries with wrong creds")
    public void loginWithWrongCreds() throws InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.userLogin("admin@test.com","wrongcreds");
        String textActual= driver.findElement(By.tagName("p")).getText();
        String textExpectd="Invalid email or password";
        Assert.assertTrue(textActual.contains(textExpectd));
        clearData();
    }
    public void clearData(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.txtPassword.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
    }
    @Test(priority = 2, description = "Admin can login successfully with valid email and password", groups = "smoke")
    public void doLogin() throws IOException {
        LoginPage loginPage=new LoginPage(driver);
        if(System.getProperty("email")!=null && System.getProperty("password")!=null){
            loginPage.userLogin(System.getProperty("email"),System.getProperty("password"));
        }
        else{
            loginPage.userLogin("admin@test.com","admin123");
        }

        String txtHeaderActual= driver.findElement(By.tagName("h2")).getText();
        String txtHeaderExpected="Admin Dashboard";

        Assert.assertEquals(txtHeaderActual,txtHeaderExpected);
        Utils.getToken(driver);
    }
    @Test(priority = 3, description = "Get user firstname", groups = "smoke")
    public void viewProfile() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElements(By.tagName("button")).get(1).click();
        String name= driver.findElement(By.name("firstName")).getAttribute("Value"); //get value from disabled text
        System.out.println(name);

        SoftAssert softAssert=new SoftAssert();
        String textActual= driver.findElement(By.tagName("h4")).getText();
        softAssert.assertTrue(textActual.contains("User Details"));
        List<WebElement> buttons= driver.findElements(By.tagName("button"));
        softAssert.assertTrue(buttons.get(2).isDisplayed());
    }
    @Test(priority = 4, description = "Admin can logout successfully", groups = "smoke")
    public void logout(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}
