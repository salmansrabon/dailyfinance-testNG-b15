package testrunner;

import config.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class AdminLoginTestRunner extends Setup {
    @Test(priority = 1, description = "Admin can login successfully with valid email and password")
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.adminLogin("admin@test.com","admin123");
        String txtHeaderActual= driver.findElement(By.tagName("h2")).getText();
        String txtHeaderExpected="Admin Dashboard";

        Assert.assertEquals(txtHeaderActual,txtHeaderExpected);
    }
    @Test(priority = 2, description = "Get user firstname")
    public void viewProfile() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElements(By.tagName("button")).get(1).click();
        String name= driver.findElement(By.name("firstName")).getAttribute("Value"); //get value from disabled text
        System.out.println(name);
    }
    @Test(priority = 3, description = "Admin can logout successfully")
    public void logout(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}
