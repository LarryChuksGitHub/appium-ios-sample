package com.saucelabs.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hchukwuji on 01/07/16. Example Appium iOS application test.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppiumiOSTest {

    private AppiumDriver driver;
    public String TESTDOID_APIKEY = ""; // Set you API key to access testdroid cloud here
    private String localCompName = "";  // set your local computer name here

    private static final String BUNDLE_ID = "com.bitbar.testdroid.BitbarIOSSample";
    private static final String SAMPLE_APP_PATH = "./app/BitbarIOSSample.ipa";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app-package", "de.zalando.mobile.debug");
        capabilities.setCapability("app-activity", "de.zalando.mobile.ui.start.SplashActivity");
        capabilities.setCapability("takesScreenshot", true);

        // host name is
        if (localCompName.equals(getLocalComputerName())) { // Local set up
            System.out.println(localCompName);
            capabilities.setCapability("appium-version", "");
            capabilities.setCapability("deviceName", "");
            capabilities.setCapability("platformName", "ios");
            capabilities.setCapability("app", SAMPLE_APP_PATH);
            driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities) {

                @Override
                public MobileElement scrollToExact(final String arg0) {

                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public MobileElement scrollTo(final String arg0) {

                    // TODO Auto-generated method stub
                    return null;
                }
            };

        }

        // Cloud Setup
        if (TESTDOID_APIKEY.equals(getAPIKEY())) { // testdoid cloud
            capabilities.setCapability("testdroid_apiKey", TESTDOID_APIKEY);
            capabilities.setCapability("testdroid_testrun", "Test run 1");
            capabilities.setCapability("testdroid_app", "latest");
            capabilities.setCapability("testdroid_target", "ios");
            capabilities.setCapability("testdroid_project", "Appium iOS Test");
            capabilities.setCapability("testdroid_device", "iPad 3 A1416 7.0.4");
            capabilities.setCapability("desired.bundleId", BUNDLE_ID);

            driver = new AppiumDriver(new URL("http://appium.testdroid.com/wd/hub"), capabilities) {
                @Override
                public MobileElement scrollToExact(final String arg0) {

                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public MobileElement scrollTo(final String arg0) {

                    // TODO Auto-generated method stub
                    return null;
                }
            };

        }

    }

    // wait 2 min until element appears else fail the test
    public void driverWait(WebElement element, final String name) {
        element = (new WebDriverWait(driver, 500)).until(ExpectedConditions.presenceOfElementLocated(By.id(name)));

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void mainPageTest() throws IOException, InterruptedException {
        WebElement element;
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        element = driver.findElement(By.name("answer2"));
        driverWait(element, "answer2");
        element.click();
        element = driver.findElement(By.name("answer2"));
        element.click();
        driver.findElement(By.name("userName")).click();
        driver.findElement(By.name("userName")).sendKeys("John Doe");
        driver.findElement(By.name("return")).click();
        driver.findElement(By.name("sendAnswer")).click();
    }

    public String getAPIKEY() {
        return TESTDOID_APIKEY;
    }

    public String getLocalComputerName() throws UnknownHostException {

        return InetAddress.getLocalHost().getHostName();

    }
}
