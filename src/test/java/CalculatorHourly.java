import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalculatorHourly {
    private String baseUrl = "http://www.paycheckcity.com/";
    private WebDriver driver;
    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();


    @Test
    public void testName() throws Exception {
//       baseUrl = "https://trello.com/login/";
         //      System.setProperty("webdriver.ie.driver", "C:\\Java\\Selenium\\IEDriverServer.exe");
             driver = new InternetExplorerDriver();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
 //       driver = new FirefoxDriver();
//        driver.get("http://www.paycheckcity.com/");
        driver.get(baseUrl+"calculator/hourly/");
        driver.manage().timeouts().implicitlyWait(101,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
