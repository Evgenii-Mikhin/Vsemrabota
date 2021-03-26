package util;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static WebDriver driver;
    public static WebDriver getDriver(){

        return driver;
    }
    public static Properties properties = TestProperties.getInstance().getProperties();

    @Before
    public void setUp() {
        String browser = properties.getProperty("browser");
        if ("firefox".equals(browser)) {
            System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
            driver = new FirefoxDriver();
        } else if ("chrome".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver .get("https://vsemrabota.ru/seekers?category=28&city=0&region=0");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver .manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @After
    public void afterMethod(){ driver.quit();
    }
}
