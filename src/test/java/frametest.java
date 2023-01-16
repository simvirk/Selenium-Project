import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class frametest {

    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void testName() {
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/a")).click();
        String currentWindow = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        Iterator<String> i = windowHandles.iterator();
        while (i.hasNext()){
            String childwindow = i.next();
            if(!childwindow.equalsIgnoreCase(currentWindow)){
                driver.switchTo().window(childwindow);
                
            }
        }

    }

    @AfterMethod
    public void tearDown() {

    }
}
