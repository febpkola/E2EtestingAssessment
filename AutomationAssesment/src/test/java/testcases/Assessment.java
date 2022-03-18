package testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestNGListeners.class)
public class Assessment extends Base {
    @BeforeMethod
    public void beforeMethod() {
        if (!driver.getCurrentUrl().equalsIgnoreCase(baseApplicationURI)) {
            driver.get(baseApplicationURI);
        }
    }

    @Test
    public void registerSuccessful() {
        String randomCharacters = RandomStringUtils.randomAlphabetic(10);
        System.out.println(randomCharacters);
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("username")).sendKeys(properties.getProperty("username"));
        driver.findElement(By.id("email")).sendKeys(randomCharacters+properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String headingText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(headingText, properties.getProperty("successfulRegistrationText"), properties.getProperty("failedRegistrationText") + " : " + headingText);
    }

    @Test
    public void registerUsingDuplicateEmail() {
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("username")).sendKeys(properties.getProperty("username"));
        driver.findElement(By.id("email")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String headingText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(headingText, properties.getProperty("duplicateEmailRegistrationText"), properties.getProperty("failedRegistrationText") + " : " + headingText);
    }


    @Test
    public void loginSuccessful() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("email")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String headingText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(headingText, properties.getProperty("successfulLoginText"), properties.getProperty("failedLoginText") + " : " + headingText);
    }

    @Test
    public void loginUsingIncorrectPassword() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("email")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("incorrectPassword"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String headingText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(headingText, properties.getProperty("unsuccessfulLoginText"), properties.getProperty("failedLoginText") + " : " + headingText);
    }

    @Test
    public void loginUsingIncorrectEmail() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("email")).sendKeys(properties.getProperty("incorrectEmail"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String headingText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(headingText, properties.getProperty("unsuccessfulLoginText"), properties.getProperty("failedLoginText") + " : " + headingText);
    }
}
