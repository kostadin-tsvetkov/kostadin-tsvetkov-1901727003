package selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/kostadintsvetkov/Downloads/chromedriver");

        driver = new ChromeDriver();
    }

    @Test
    public void openCart() {

        driver.navigate().to("https://www.technopolis.bg/bg/");

        driver.findElement(By.className("modal-close")).click();

        driver.findElement(By.className("item-cart")).click();

        WebElement result = driver.findElement(By.id("refreshCartFormID"));
        Assert.assertNotNull(result);

        driver.close();
    }

    @Test
    public void openCartAndReturn() {

        driver.navigate().to("https://www.technopolis.bg/bg/");

        driver.findElement(By.className("modal-close")).click();

        driver.findElement(By.className("item-cart")).click();

        driver.navigate().back();

        Assert.assertNotNull(driver.findElement(By.className("modal-close")));

        driver.close();
    }

    @Test
    public void addToFavourites() {
        driver.navigate().to("https://www.technopolis.bg/bg/Mobilni-telefoni/Smartfon-GSM-XIAOMI-REDMI-9C-GRAY/p/579634");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0, 250)");

        driver.findElement(By.className("favorites")).click();

        Assert.assertEquals("https://www.technopolis.bg/bg/login", driver.getCurrentUrl());

        driver.close();
    }

    @Test
    public void addToCart() {
        driver.navigate().to("https://www.technopolis.bg/bg/Mobilni-telefoni/Smartfon-GSM-XIAOMI-REDMI-9C-GRAY/p/579634");

        driver.findElement(By.id("addToCartButton579634")).click();

        Assert.assertNotNull(driver.findElement(By.className("checkout-steps")));

        driver.close();
    }

    @Test
    public void search() {
        driver.navigate().to("https://www.technopolis.bg/bg/");

        driver.findElement(By.className("modal-close")).click();

        WebElement search = driver.findElement(By.id("search"));

        search.sendKeys("iphone");
        search.submit();

        Assert.assertNotEquals(0, driver.findElements(By.className("lazyloaded")).size());

        driver.close();
    }
}
