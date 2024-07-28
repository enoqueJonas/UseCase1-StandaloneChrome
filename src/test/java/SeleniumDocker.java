import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumDocker {

    public static ChromeOptions options;
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static String remote_url_chrome = "http://localhost:4444/";

    @BeforeClass
    public static void Setup() throws MalformedURLException {
        options = new ChromeOptions();
        driver.set(new RemoteWebDriver(new URL(remote_url_chrome), options));
    }

    @Test
    public void TestSteps() throws InterruptedException {
        WebDriver webDriver = getDriver();
        webDriver.navigate().to("https://www.google.com");
        webDriver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html#");
        webDriver.findElement(By.xpath("//*[@id=\"menuToggle\"]/input")).click();
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"menu\"]/a[2]/li")).click();
        webDriver.findElement(By.xpath("//*[@id=\"usr\"]")).sendKeys("sa");
        webDriver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys("sa");
        webDriver.findElement(By.xpath("//*[@id=\"second_form\"]/input")).click();

        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"ShoeType\"]"));
        String expectedFirstProdCategory = "Formal Shoes";

        Assert.assertEquals(expectedFirstProdCategory,
                webElement);

        webDriver.close();
    }

    private WebDriver getDriver(){
        return driver.get();
    }
}
