import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalculatorHourly {
    private String baseUrl = "http://www.paycheckcity.com/";
    private WebDriver driver;
//    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//WebElement source = driver.findElement(By.xpath(".//a[text()=\"Second card\" and @class=\"list-card-title js-card-name\"]"));
//    WebElement target = driver.findElement(By.xpath("//textarea[contains(text(),'Advanced')]"));

    @Test
    public void fillParametersAndCalculateHourly() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://www.paycheckcity.com/");
        driver.get(baseUrl+"calculator/hourly/");
        driver.manage().timeouts().implicitlyWait(101,TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.findElement(By.id("calcType.rates0.payRate")).clear();
        driver.findElement(By.id("calcType.rates0.payRate")).sendKeys("50");
        driver.findElement(By.id("calcType.rates0.hours")).clear();
        driver.findElement(By.id("calcType.rates0.payRate")).sendKeys("160");
        driver.findElement(By.id("addRate")).click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("\\\\span[@class=\"resultData\"]\\..\\..\\..\\div[@title=\"Net Pay\"]")));

 //       source = driver.findElement(By.xpath(".//a[text()=\"Second card\" and @class=\"list-card-title js-card-name\"]"));

    }

}


/*Pay Rate #1 = 50
Hours #1 = 160

Далее нажмите AddRate и введите в поля слующее:

Pay Rate #2 = 80
Hours #2 = 8

    Проверьте, что в поле Gross Pay появилось расчитанное значение 8640.

    В своей работе используйте @Before и @After методы из пакета TestNg.

    Настройте тесты так, чтобы браузер открывался один раз для прохождения обоих тестов.

    Также, создайте testng.xml и опишите в нем свой сьют.

    Добавьте написанные тесты в группы P1 и regression.

    Запустите тесты с помощью testng.xml
*/