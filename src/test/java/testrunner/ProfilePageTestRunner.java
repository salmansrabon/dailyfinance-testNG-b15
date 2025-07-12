package testrunner;

import config.Setup;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class ProfilePageTestRunner extends Setup {
    @BeforeTest
    public void setAuth() throws ParseException, IOException, InterruptedException {
        Utils.setAuth(driver);
    }
    @Test
    public void visitProfilePage() throws InterruptedException {
        driver.get("http://localhost:3000/user/9759edaa-3f58-4162-937e-452101a7f90d");
    }
}
