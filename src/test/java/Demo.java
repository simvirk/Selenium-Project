import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

public class Demo {
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://pragra.io");
        //driver.get("https://zoom.us");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test1(){
        //WebElement element = driver.findElement(By.xpath("//input[@id='email']"));
        //element.sendKeys("simran.io", Keys.ENTER);
        //WebElement element = driver.findElement(By.xpath("//*[@id='signupfree']/a[@href='https://zoom.us/signup']"));
        //element.click();
        //WebElement email = driver.findElement(By.xpath("//input[@id='year']"));
        //email.sendKeys("1995");
        WebElement element = driver.findElement(By.xpath("//li/a[@href='https://explore.zoom.us/en/contactsales']"));
        element.click();
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("Sam@pragra.io");
        WebElement company = driver.findElement(By.xpath("//input[@id='company']"));
        company.sendKeys("Pragra");
        WebElement name = driver.findElement(By.xpath("//input[@id='first_name']"));
        name.sendKeys("Sam");
        WebElement lastName = driver.findElement(By.xpath("//input[@id='last_name']"));
        lastName.sendKeys("V");
        WebElement EmployeeCount = driver.findElement(By.xpath("//select[@id='employee_count']"));
        EmployeeCount.click();

        WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
        phone.sendKeys("234555");
        //WebElement country = driver.findElement(By.xpath(""));
        //country.isSelected("Canada");
    }

    @Test
    public void test2() throws InterruptedException{
        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.xpath("//li/a[@href='https://explore.zoom.us/en/contactsales']")).click();
        //System.out.println(driver.getCurrentUrl());
        driver.get("https://zoom.us");
        //System.out.println(driver.getPageSource());
        Thread.sleep(10000);
        driver.close();
    }

    @Test
    public void test3() throws InterruptedException {
        driver.get("https://zoom.us");
        ((JavascriptExecutor) driver).executeScript("alert('Hello how are you?')");

        Alert alert = driver.switchTo().alert();
        Thread.sleep(2000);
        System.out.println(alert.getText());
        alert.dismiss();
    }

    @Test
    public void test4() throws InterruptedException {
        driver.get("https://www.w3schools.com/html/html_iframe.asp");
        WebElement frame = driver.findElement(By.xpath("//iframe[@title='W3Schools HTML Tutorial']"));
        driver.switchTo();
        WebElement loginbtn = driver.findElement(By.id("w3loginbtn"));
        loginbtn.click();
    }

    @Test
    public void test5() throws InterruptedException{
        driver.get("https://pragra.io");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/nav/a[2]"));
        element.click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.navigate().to("https://zoom.us");
    }

    @Test
    public void test6() throws InterruptedException{
        driver.get("https://pragra.io");
        WebDriver.Window window = driver.manage().window();
        window.setSize(new Dimension(600,600));
        Thread.sleep(5000);
        window.setPosition(new Point(50,100));
        Thread.sleep(2000);

        String currentWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://zoom.us");
        String newWindow = driver.getWindowHandle();

        window.setSize(new Dimension(600,600));
        Thread.sleep(5000);
        window.setPosition(new Point(600,100));
        Thread.sleep(2000);

        driver.switchTo().window(currentWindow);
        Thread.sleep(2000);
        driver.switchTo().window(newWindow);
    }
    @Test
    public void Cookie() throws InterruptedException {
        driver.get("https://zoom.us/pricing");
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
        driver.manage().deleteCookieNamed("_zm_currency");
        Thread.sleep(2000);
        Cookie cookie = new Cookie("_zm_currency","USD");
        driver.manage().addCookie(cookie);
        Thread.sleep(2000);
        driver.navigate().refresh();

    }
    @Test
    public void Element() throws InterruptedException {
        driver.get("https://explore.zoom.us/en/contactsales/");
        WebElement element = driver.findElement(By.xpath("/html/body/main/div/div[1]/div[3]/form/div"));
        element.submit();

        WebElement company = driver.findElement(By.xpath("//input[@id='company']"));
        company.sendKeys("Pragra");
        Thread.sleep(2000);
        company.clear();
    }

    @Test
    public void Check(){
        driver.get("https://www.pragra.io/skill-development/aws-associate-certification");
        WebElement element = driver.findElement(By.xpath("//input[@type='checkbox']"));
        element.click();
        if (element.isSelected()){
            System.out.println("selected");
        }
        else {
            System.out.println("not selected");
        }


    }

    @Test
    public void hide() throws IOException {
        driver.get("https://zoom.us/");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul[2]/li[5]/a"));
        //((JavascriptExecutor) driver).executeScript("$(arguments[0]).hide()", element);
        //if (element.isDisplayed()){
         //   System.out.println("displayed");
        //}
       //else {
         //   System.out.println("not displayed");
       // }

        System.out.println(element.getText());
        System.out.println(element.getCssValue("background-color"));
        System.out.println(element.getCssValue("font-size"));

        File file = element.getScreenshotAs(OutputType.FILE);
        Files.copy(file.toPath(), Paths.get("screenshot1.png"));

        File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot2.toPath(), Paths.get("file.png"));
    }

    @Test
    public void Select(){
        driver.get("https://explore.zoom.us/en/contactsales/");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
        Select select = new Select(element);
        select.selectByIndex(0);
        select.selectByValue("AS");
        select.selectByVisibleText("District of Columbia");
    }

    @Test
    public void navigation() throws InterruptedException{
        driver.get("https://www.shopify.com/ca");
        WebElement start = driver.findElement(By.xpath("//*[@id=\"ShopifyMainNav\"]/ul[1]/li[1]/div/button"));
        WebElement setUp = driver.findElement(By.xpath("//*[@id=\"Popover1\"]/div/ul/li[4]/div/button"));
        WebElement themes = driver.findElement(By.xpath("//*[@id=\"Popover4\"]/div/ul/li[2]/a"));


        //start.click();
         //setUp.click();
        //themes.click();
       //WebElement explore = driver.findElement(By.xpath("//*[@id=\"Main\"]/section[1]/div/section[1]/div/div/div[1]/a"));
       // explore.click();

        Actions actions = new Actions(driver);

        actions.moveToElement(start).pause(1000).moveToElement(setUp).pause(1000).moveToElement(themes).click().build().perform();
    }

    @Test
    public void dragDrop() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        WebElement src = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(src,100,50).build().perform();
        Thread.sleep(1000);
    }

    @Test
    public void contextClick() throws InterruptedException{
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement element = driver.findElement(By.xpath("/html/body/div/section/div/div/div/p/span"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).contextClick().build().perform();
        Thread.sleep(5000);

        driver.findElement(By.xpath("/html/body/ul/li[3]/span")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println("alert.getText() =" + alert.getText());
        Thread.sleep(2000);
        alert.dismiss();
    }

    @Test
    public void testDoubleClick(){

    }




    @AfterSuite(alwaysRun = true)
    public void tearDown() throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }
}

