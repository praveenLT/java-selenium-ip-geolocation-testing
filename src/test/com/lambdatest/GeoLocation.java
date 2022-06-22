package com.lambdatest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GeoLocation {

    private RemoteWebDriver driver;
    private String Status = "failed";
    public void setup() throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "Extension Test");
        caps.setCapability("name", this.getClass().getName());
        caps.setCapability("plugin", "git-java");
        caps.setCapability("geoLocation","AR"); //Geolocation capability, check LambdaTest Capability Generator

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    public void geoLocationTest() throws InterruptedException {

        System.out.println("Loading Url");

        driver.get("https://www.iplocation.net/");

       
        Status = "passed";
        Thread.sleep(15000);

        System.out.println("TestFinished");

    }


    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        GeoLocation test = new GeoLocation();
        test.setup();
        test.geoLocationTest();
        test.tearDown();
    }

}