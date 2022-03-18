package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public static Properties properties = null;
    public static WebDriver driver = null;
    private FileInputStream fileInputStream = null;
    public static String baseApplicationURI = null;
    private String loginPage = null;
    private String registrationPage = null;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();


        properties = new Properties();

        try {
            fileInputStream = new FileInputStream("src/test/java/utils/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseApplicationURI = properties.getProperty("baseApplicationURI");
        loginPage = properties.getProperty("loginPage");
        registrationPage = properties.getProperty("registrationPage");

        driver.get(baseApplicationURI);

    }

    @AfterSuite
    public void afterSuite() throws InterruptedException {
        driver.close();
        driver.quit();
    }
}
