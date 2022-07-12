package Base;


import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import reporting.utility.utility;

public class commonApi {

    public WebDriver driver;
    protected Properties properties = utility.loadProperties();
    /*Logger LOG = LogManager.getLogger(commonApi.class.getName());*/


    String browserstackUsername = utility.decode(properties.getProperty("browserStack.username"));
    String browserstackPassword =utility.decode(properties.getProperty("browserStack.password"));
    String sauceUsername = utility.decode(properties.getProperty("saucelabs.username"));
    String saucePassword = utility.decode(properties.getProperty("saucelabs.password"));
    String implicitWait = properties.getProperty("implicit.wait","3");
    String takeScreenshot = properties.getProperty("take.screenshot", "false");


    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
       /* if (takeScreenshot.equalsIgnoreCase("true")){
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
        }*/
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    @Parameters({"useCloud", "env", "os", "osVersion", "browser", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(boolean useCloud, String env, String os, String osVersion, String browser, String browserVersion, String url) throws MalformedURLException, InterruptedException {
        if (useCloud) {
            if (env.equalsIgnoreCase("browserstack")) {
                cloudDrive(env, browserstackPassword, browserstackUsername, os, osVersion, browser, browserVersion);
            } else if (env.equalsIgnoreCase("sauceLabs")) {
                cloudDrive(env, sauceUsername, saucePassword, os, osVersion, browser, browserVersion);
            }

        } else {
            localDrive(browser, os);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));
        driver.get(url);
        Thread.sleep(7000);

    }

   /* @AfterMethod
    public void tearDown() {
        driver.close();

    }*/

    public void cloudDrive(String env, String username, String password, String os, String osVersion, String browser, String browserVersion) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("osVersion", osVersion);
        cap.setCapability("browser", browser);
        cap.setCapability("browserVersion", browserVersion);

        if (env.equalsIgnoreCase("browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://"+username+":"+ password+"@hub-cloud.browserstack.com:80/wd/hub"), cap);
        } else if (env.equalsIgnoreCase("sauceLabs")) {
            driver = new RemoteWebDriver(new URL("http://"+username+":"+password+"@ondemand.saucelabs.com:80/wd/hub"), cap);
        }

    }

    public void localDrive(String browser, String os) {
        if (os.equalsIgnoreCase("win")) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", utility.currentDir+"\\drive\\win\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("fireFox")) {
                System.setProperty("webdriver.gecko.driver", utility.currentDir+"\\drive\\win\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } else if (os.equalsIgnoreCase("mac")) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", utility.currentDir+"/drive/mac/chromedriver");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("fireFox")) {
                System.setProperty("webdriver.firefox.driver", utility.currentDir+"/drive/mac/geckodriver-v0.31.0-macos.tar.gz");
                driver = new FirefoxDriver();
            }
        }
    }

    public String getTitle() {
        System.out.println(driver.getTitle());
        return driver.getTitle();
    }


    public void clickBtn(WebElement element) {

        element.click();
        }


    public void type(WebElement element, String input) {

        element.sendKeys(input);
    }

    public String getelmText(WebElement element) {

            return element.getText();
    }

    public boolean isDisplayed(WebElement element) {

            return element.isDisplayed();
        }

    public void typeAndEnter(WebElement element, String input) {
        element.sendKeys(input, Keys.ENTER);
    }

    public void selectFromDropDown(WebElement dropDownElmnt, String option) {
        Select dropDown = new Select(dropDownElmnt);
        try {
            dropDown.selectByValue(option);
        } catch (Exception e) {
            dropDown.selectByVisibleText(option);
        }
    }

    public void hoverOver(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }


    public void scrollDwn(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.scrollToElement(element).build().perform();
    }

    public void dragAndDrop(WebDriver driver, WebElement draggable, WebElement droppable) {
        Actions action = new Actions(driver);
        action.dragAndDrop(draggable, droppable).build().perform();
    }

    public void clickWithActions(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.click(element).build().perform();
    }

    public void typeAndTabKey(WebElement element, String input) {

        element.sendKeys(input, Keys.TAB);

    }

    public void scrollToViewJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("argument[0].scrollIntoView(true);", element);
    }
    public void scrollDown(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,5400);");
    }

        public void clickWithJS (WebElement element){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeAsyncScript("argument[0].click();", element);

        }
        public void waitTime(long seconds) throws InterruptedException {
        Thread.sleep(seconds);
        }
    public void captureScreenshot() {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public void takeScreenshot(String screenshotName){
        DateFormat df = new SimpleDateFormat("MMddyyyyHHmma");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "\\screenshots\\"+screenshotName+" "+df.format(date)+".jpeg"));
            LOG.info("Screenshot captured");
        } catch (Exception e) {
            String path = utility.utility.currentDir+ "/screenshots/"+screenshotName+" "+df.format(date)+".jpeg";
            LOG.info("Exception while taking screenshot "+e.getMessage());
        }
    }*/

}