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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.SelectableChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

public class HomeTest {
    WebDriver driver;

    @BeforeSuite
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // driver.get("https://www.pragra.io");
         //get will open in the browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(enabled = false)
    public void testName() throws InterruptedException {
        //WebElement email = driver.findElement(By.xpath("//div[@id='signupfree']/a"));
        WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/nav/a[2]"));
        //WebElement email = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul[1]/li[11]/a"));
        // WebElement email = driver.findElement(By.xpath("//*[@id=\"email"));
        //WebElement email = driver.findElement(By.xpath("//*[@id=\"company\"]"));
        // WebElement email = driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul[1]/li[11]/a"))
        //*[@id="email"]
        //email.click();
        //WebElement element = driver.findElement(By.xpath("//input[@id='year']"));
        //element.sendKeys("25");

        // System.out.println(driver.getCurrentUrl());

        //driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/div/a)"));
        System.out.println(driver.getCurrentUrl());
        //driver.get("https://www.pragra.io");
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.keyDown(Keys.COMMAND).click(element).build().perform();

        String currentWindow = driver.getWindowHandle();
        String newWindow = "";

        Set<String> windowHandles = driver.getWindowHandles(); // all windows

        for (String window : windowHandles)
            if (!window.equals(currentWindow)) {
                newWindow = window;
                driver.switchTo().window(window);
            }

        Thread.sleep(5000);
    driver.switchTo().window(currentWindow);
    Thread.sleep(5000);
        driver.switchTo().window(newWindow);
        Thread.sleep(5000);
        driver.switchTo().window(currentWindow);
        driver.close();
        //driver.quit();
       // System.out.println(driver.getTitle());


    }

    @Test(enabled = false)
    public void testNavigation() throws InterruptedException, MalformedURLException {
        driver.get("https://pragra.io");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/nav/a[1]"));
        element.click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();

        driver.navigate().to(new URL("https://zoom.us/"));
    }

    @Test(enabled = false)
    public void testOptions() throws InterruptedException{
        driver.get("https://pragra.io");
        WebDriver.Window window = driver.manage().window();
        window.fullscreen();
        Thread.sleep(5000);
        window.setSize(new Dimension(600,1200));
        Thread.sleep(5000);
        window.setPosition(new Point(40,20));

        String currentWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://zoom.us");
        String newWindow = driver.getWindowHandle();
        window.setSize(new Dimension(600,1200));
        Thread.sleep(5000);
        window.setPosition(new Point(650,20));


        driver.switchTo().window(currentWindow);
        Thread.sleep(5000);
        driver.switchTo().window(newWindow);
    }

    @Test(enabled = false)
    public void testCookie() throws InterruptedException{       //deleting a cookie and creating a cookie
        driver.get("https://zoom.us/pricing");
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
        driver.manage().deleteCookieNamed("_zm_currency");
        Thread.sleep(10000);
        Cookie cookie = new Cookie("_zm_currency","USD");
        driver.manage().addCookie(cookie);
        Thread.sleep(5000);
        driver.navigate().refresh();
    }

    @Test(enabled = false)
    public void webElement() throws InterruptedException {
        driver.get("https://zoom.us/en/contactsales/");
        //WebElement element = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/div[1]/div[2]/div/div[3]/nav/a[1]"));
        WebElement element = driver.findElement(By.xpath("//form[@class='FormStandard ']"));
        element.submit();

        WebElement email = driver.findElement(By.id("email"));

        System.out.println(email.getAttribute("placeholder"));

        email.sendKeys("sim@pragra");
        driver.findElement(By.id("company")).sendKeys("Example Corp");
        Thread.sleep(5000);
        email.clear();
    }

    @Test(enabled = false)
    public void testCheckBox(){
       driver.get("https://www.pragra.io/skill-development/aws-associate-certification");

      WebElement element = driver.findElement(By.xpath("//input[@type='checkbox']"));
      //element.click();
      if(element.isSelected()) {
          System.out.println("IT SELECTED");
      }else{
              System.out.println("NOT selected");
          }
      }

      @Test(enabled = false)
      public void testEnabled() {
          driver.get("https://zoom.us");
          WebElement element = driver.findElement(By.xpath("//div[@id='signupfree']/a"));
          ((JavascriptExecutor) driver).executeScript("$(arguments[0]).hide()", element);

          if (element.isDisplayed()) {
              System.out.println("Yes element displayed");
          } else {
              System.out.println("not displyed");
           }

          if (element.isEnabled()) {
              System.out.println("yes enabled");
          } else {
              System.out.println("not enabled");
          }
      }

          @Test(enabled = false)
          public void testColor() throws InterruptedException, IOException {
              driver.get("https://zoom.us");
              WebElement element = driver.findElement(By.xpath("//div[@id='signupfree']/a"));
              ((JavascriptExecutor) driver).executeScript("$(arguments[0]).attr('disabled','disabled')");
              System.out.println(element.getText());

              System.out.println(element.getCssValue("background-color"));
              System.out.println(element.getCssValue("width"));
              File file = element.getScreenshotAs(OutputType.FILE);
              Files.copy(file.toPath(), Paths.get("screenshot.png"));     //copy to disk, give file to path

              File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);  //full screenshot, using driver.cast
              ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
              Files.copy(screenshot.toPath(),Paths.get("fullScreen.png"));

          }

          @Test(enabled = false)
          public void testSelect(){
        driver.get("https://explore.zoom.us/en/contactsales/");
        WebElement countrySelect = driver.findElement(By.xpath("//select[@id='country']"));
              Select select = new Select(countrySelect);
              System.out.println(select.isMultiple());
            // select.selectByValue("AS");
             //select.selectByVisibleText("Bahamas");
             select.selectByIndex(2);
          }

          @Test(enabled = false)
          public void testSelect2(){
              driver.get("https://explore.zoom.us/en/contactsales/");
              WebElement countSelect = driver.findElement(By.xpath("//select[@id='employee_count']"));
              Select select = new Select(countSelect);
              System.out.println(select.isMultiple());
              select.selectByValue("Just Me");

          }

          @Test(enabled = false)
          public void testMultiple(){
        driver.navigate().to("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

        WebElement iframeResult = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframeResult);

        WebElement cars = driver.findElement(By.name("cars"));
        Select carSelect = new Select(cars);

              System.out.println(carSelect.isMultiple());

              carSelect.selectByIndex(1);
              carSelect.selectByIndex(3);
    }

    @Test(enabled = false)
    public void testNavigate() throws InterruptedException{
       // driver.get("https://www.cn.ca/en/");
        WebElement services = driver.findElement(By.id("ctl06__10be2b992589bb8_repMainNav_topLevelLink_1"));
        WebElement trucking = driver.findElement(By.id("ctl06__10be2b992589bb8_repMainNav_repSecondLevel_1_secondLevelLink_2"));
        WebElement cnExpress = driver.findElement(By.id("ctl06__10be2b992589bb8_repMainNav_repSecondLevel_1_repThirdLevel_2_thirdLevelLink_1"));
        WebElement jobGallery = driver.findElement(By.id("ctl06__10be2b992589bb8_repMainNav_repSecondLevel_1_repThirdLevel_2_repFourthLevel_1_fourthLevelLink_0"));


        Actions actions = new Actions(driver);
        actions.moveToElement(services,100,0).build().perform();

        //actions.moveToElement(services).pause(1000).moveToElement(trucking).pause(1000)
                       // .moveToElement(cnExpress).pause(1000)
                      //  .moveToElement(jobGallery).click().build().perform();

        actions.moveToElement(services, 100,0).build().perform();


        WebElement punjabi = driver.findElement(By.xpath("//*[@id=\"BaseLayout_phtopcontent_1_divControlContent\"]/div/p[1]/a"));
        punjabi.click();
    }

    @Test(enabled = false)
    public void testDragNDrop() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        WebElement src = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(src,0,200).build().perform();

        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement element = driver.findElement(By.xpath("/html/body/div/section/div/div/div/p/span"));

        actions.moveToElement(element).contextClick().build().perform();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/ul/li[1]")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println("alert.getText() = " + alert.getText());
        alert.dismiss();
    }

    @Test(enabled = false)
    public void testDoubleClick(){

    }

    @Test
    public void testiFrame(){
driver.get("https://www.w3schools.com/html/html_iframe.asp");
        WebElement frame = driver.findElement(By.xpath("//iframe[@title='W3Schools HTML Tutorial']"));
        driver.switchTo().frame(frame);
        WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"w3loginbtn\"]"));
        loginbtn.click();

    }


    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}
