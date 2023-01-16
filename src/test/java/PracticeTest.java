import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

public class PracticeTest {

    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(enabled = false)
    public void testNavigation() throws InterruptedException {
        driver.get("https://www.pragra.io/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/nav/a[1]"));
        element.click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.navigate().to("https://www.amazon.ca/");

    }

    @Test(enabled = false)
    public void testOption() throws InterruptedException {
        driver.get("https://www.pragra.io/");
        WebDriver.Window window = driver.manage().window();
        window.fullscreen();
        Thread.sleep(5000);
        window.setSize(new Dimension(600,600));
        Thread.sleep(5000);
        window.setPosition(new Point(40,100));

        String currentwindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW);   //switch to new window

        driver.get("https://zoom.us/");         //load new site
        String newwindow = driver.getWindowHandle();
        window.setSize(new Dimension(600,600));
        Thread.sleep(5000);
        window.setPosition(new Point(640,100));

        driver.switchTo().window(currentwindow);
        Thread.sleep(5000);
        driver.switchTo().window(newwindow);
    }

    @Test(enabled = false)
    public void testCookie() throws InterruptedException {
        driver.get("https://zoom.us/pricing");
        Set<Cookie> cookies = driver.manage().getCookies();//read all the cookies
        System.out.println(cookies);  //print
        driver.manage().deleteCookieNamed("_zm_currency");
        Thread.sleep(20000);

    }

    @Test
    public void testPath(){
        driver.get("https://zoom.us/");
        driver.findElement(By.xpath("//li//a[@href='https://zoom.us/signup']")).click();
        WebElement element = driver.findElement(By.xpath("//input[@id=\"year\"]"));
        element.sendKeys("24",Keys.ENTER);

    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
