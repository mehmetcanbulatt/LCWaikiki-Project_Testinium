package BaseTest;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;
    protected String baseUrl = "https://www.lcwaikiki.com/tr-TR/TR";
    ChromeOptions options = new ChromeOptions();
    DesiredCapabilities chrome = DesiredCapabilities.chrome();

    public DesiredCapabilities setLocaleChromeCapabilities() {
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-notifications");
        options.addArguments("disable-popup-blocking");
        options.setExperimentalOption("w3c", false);
        options.addArguments("enable-features=NetworkServiceInProcess");
        chrome.setCapability(ChromeOptions.CAPABILITY, options);
        return chrome;
    }
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(setLocaleChromeCapabilities());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 45, 150);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @After
    public void close() {
        driver.quit();
    }
}
