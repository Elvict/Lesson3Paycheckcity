import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class CalculatorHourly {
    private String baseUrl = "http://www.paycheckcity.com/";
    private WebDriver driver;
    private String expectedResult = "8640";

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://www.paycheckcity.com/");
        driver.get(baseUrl+"calculator/hourly/");
        System.out.println(baseUrl+"calculator/hourly/");
        driver.manage().timeouts().implicitlyWait(101,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void fillParametersAndCalculateHourly() throws Exception {
 //       String expectedResult = "8640";
        driver.findElement(By.id("calcType.rates0.payRate")).clear();
        driver.findElement(By.id("calcType.rates0.payRate")).sendKeys("50");
        driver.findElement(By.id("calcType.rates0.hours")).clear();
        driver.findElement(By.id("calcType.rates0.hours")).sendKeys("160");
        driver.findElement(By.id("addRate_label")).click();
        WebElement dynamicElement1 = (new WebDriverWait(driver, 101))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("calcType.rates1.payRate")));
        driver.findElement(By.id("calcType.rates1.payRate")).clear();
        driver.findElement(By.id("calcType.rates1.payRate")).sendKeys("80");
        driver.findElement(By.id("calcType.rates1.hours")).clear();
    //    driver.findElement(By.id("generalInformation.grossPayAmount")).clear();
        driver.findElement(By.id("calcType.rates1.hours")).sendKeys("8");
        driver.findElement(By.id("generalInformation.grossPayAmount")).click();

        String actualResult
                = driver.findElement(By.id("generalInformation.grossPayAmount")).getAttribute("value");
        System.out.println("expectedResult = "+expectedResult);
        System.out.println("actualResult = "+actualResult);
        assertEquals(actualResult, expectedResult);

        }
    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
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