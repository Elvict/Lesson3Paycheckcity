import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class CalculatorSalary {
    private String baseUrl = "http://www.paycheckcity.com/";
    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        //       baseUrl = "https://trello.com/login/";
        //       System.setProperty("webdriver.ie.driver", "C:\\Java\\Selenium\\IEDriverServer.exe");
        //       WebDriver driver = new InternetExplorerDriver();
        driver = new FirefoxDriver();
//        driver.get("http://www.paycheckcity.com/");
        driver.get(baseUrl+"calculator/salary/");
        driver.manage().timeouts().implicitlyWait(101,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void fillParametersAndCalculateSalary() throws Exception {
        driver.findElement(By.id("calcDate")).clear();
        driver.findElement(By.id("calcDate")).sendKeys("2016-08-31");
        new Select(driver.findElement(By.id("state"))).selectByVisibleText("California");
        driver.findElement(By.id("generalInformation.grossPayAmount")).clear();
        driver.findElement(By.id("generalInformation.grossPayAmount")).sendKeys("10000");
        new Select(driver.findElement(By.id("generalInformation.grossPayMethodType"))).selectByVisibleText("Pay Per Period");
        new Select(driver.findElement(By.id("generalInformation.payFrequencyType"))).selectByVisibleText("Monthly");
  //      driver.findElement(By.id("generalInformation.exemptFederal1")).click();
        if ( !driver.findElement(By.id("generalInformation.exemptFederal1")).isSelected() )
        {
            driver.findElement(By.id("generalInformation.exemptFederal1")).click();
        }

    //    driver.findElement(By.id("calculate")).click();
        driver.get(baseUrl+"calculator/salary/result");
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("\\\\span[@class=\"resultData\"]\\..\\..\\..\\div[@title=\"Net Pay\"]")));
        driver.manage().timeouts().implicitlyWait(101,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        String expectedResult = "$8,387.79";
        String actualResult = driver.findElement(By.xpath("\\\\span[@class=\"resultData\"]\\..\\..\\..\\div[@title=\"Net Pay\"]")).getAttribute("value");
//        while(actualResult.contains(Character.toString((char)160))) {
//            String replace = actualResult.replace(Character.toString((char)160), "");
//            actualResult=replace;
//        }
//
        assertEquals(actualResult, expectedResult);
//        (new WebDriverWait(driver,10))
//                .until(ExpectedConditions.textToBePresentInElement(result,expectedResult));


    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
