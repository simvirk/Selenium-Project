import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LocatorsTest {
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://www.pragra.io");
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void testName() throws InterruptedException{
       //WebElement email = driver.findElement(By.id("email"));
       //email.sendKeys("atin@pragra.io");
       //Thread.sleep(5000);
       //email.sendKeys(Keys.RETURN);

       // WebElement apply_now = driver.findElement(By.linkText("APPLY NOW"));
        //apply_now.click();

        WebElement apply_now = driver.findElement(By.partialLinkText("NOW"));
        apply_now.click();

    }

    @Test
    public void testGoogle() {
       // driver.findElement(By.name("q")).sendKeys("selenium",Keys.RETURN);
        List<WebElement> a = driver.findElements(By.tagName("a"));
        System.out.println("Total links" + a.size());

        driver.findElement(By.className("goxjub")).click();
    }

    @AfterSuite
    public void tearDown() throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }
}
