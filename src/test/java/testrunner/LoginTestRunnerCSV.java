package testrunner;

import config.LoginDataSet;
import config.Setup;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTestRunnerCSV extends Setup {
    @Test(dataProvider = "LoginDataSet", dataProviderClass = LoginDataSet.class)
    public void doLogin(String email, String password){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.userLogin(email,password);

        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.doLogout();
    }
}
