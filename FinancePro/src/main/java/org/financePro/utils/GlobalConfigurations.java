package org.financePro.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GlobalConfigurations {
    private WebDriver driver;
    private static GlobalConfigurations myObj;
    PropertyFileReader properties = new PropertyFileReader();
    public static GlobalConfigurations getInstance(){
        if (myObj == null){
            myObj = new GlobalConfigurations();
            return myObj;
        }else {
            return myObj;
        }
    }
    public WebDriver getDriver(){
        return driver;
    }

    public void openBrowser(String browser){
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments("--remote-allow-origins=*");
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
    }

    public void navigateToURL(){
        String url = properties.getProperty("config","url");
        driver.get(url);
    }
    public WebElement waitUntilNextElementAppears(By locator, int timeout){
        return new WebDriverWait(GlobalConfigurations.getInstance().getDriver(), Duration.ofSeconds(timeout)).
                until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public WebElement waitUntilNextElementClickable(By locator, int timeout){
        return new WebDriverWait(GlobalConfigurations.getInstance().getDriver(), Duration.ofSeconds(timeout)).
                until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void verifyAlert(String expectedAlertText){
        String alert = properties.getProperty("globalObjects","alert.element");
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(alert),60);
        String alertMessage = driver.findElement(By.xpath(alert)).getText();
        assert alertMessage.equals(expectedAlertText) : alertMessage;
        driver.findElement(By.xpath(alert)).click();
    }
    public void closeBrowser(){
        driver.close();
    }
}
