import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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

        driver = new FirefoxDriver();
        driver.get(baseUrl+"calculator/salary/");
        driver.manage().timeouts().implicitlyWait(101,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void fillParametersAndCalculateSalary() throws Exception {
        String expectedResult = "$8,387.79";
        
        driver.findElement(By.id("calcDate")).clear();
        driver.findElement(By.id("calcDate")).sendKeys("08/31/2016");
        driver.findElement(By.id("state")).clear();
        driver.findElement(By.id("state")).sendKeys("California");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("generalInformation.grossPayAmount")).clear();
        driver.findElement(By.id("generalInformation.grossPayAmount")).sendKeys("10000");
        driver.findElement(By.id("generalInformation.grossPayMethodType")).clear();
        driver.findElement(By.id("generalInformation.grossPayMethodType")).sendKeys("Pay Per Period");
        driver.findElement(By.id("generalInformation.payFrequencyType")).clear();
        driver.findElement(By.id("generalInformation.payFrequencyType")).sendKeys("Monthly");
        if ( !driver.findElement(By.id("generalInformation.exemptFederal1")).isSelected() )
        {
            driver.findElement(By.id("generalInformation.exemptFederal1")).click();
        }
        driver.findElement(By.id("calculate")).click();
        System.out.println("All data prepared");

        WebElement dynamicElement = (new WebDriverWait(driver, 101))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("resultData")));
        System.out.println("Net Pay is presented");

        String actualResult
                = driver.findElement(By.xpath("//span[@class='resultDef' and text()='Net Pay']/following-sibling::span[1]")).getText();
        System.out.println("expectedResult = "+expectedResult);
        System.out.println("actualResult = "+actualResult);
        assertEquals(actualResult, expectedResult);
    }

    @AfterMethod
    public void tearDown() throws Exception {
//        driver.quit();
    }
}
