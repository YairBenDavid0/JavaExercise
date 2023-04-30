import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;
    protected GoogleSearchPage searchPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.co.il/");
        searchPage = new GoogleSearchPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected void waitForPageToLoad(ExpectedCondition<Boolean> expectedCondition, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(expectedCondition);
    }

    protected void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }
}
