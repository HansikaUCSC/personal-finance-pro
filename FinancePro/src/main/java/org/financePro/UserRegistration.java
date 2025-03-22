package org.financePro;

import com.mailosaur.MailosaurException;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.OTPUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class UserRegistration {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    OTPUtils otpUtils;

    public void signUp(String name,String email,String password, String confrimPassword) throws MailosaurException, IOException {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//a[@href='/terms']"),30);
        driver.findElement(By.xpath("//a[@href='/terms']")).click();
        driver.findElement(By.id("agreeCheckbox")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_next"),30);
        driver.findElement(By.id("btn_next")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_name"),30);
        driver.findElement(By.id("txt_name")).sendKeys(name);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_email"),30);
        driver.findElement(By.id("txt_email")).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_password"),30);
        driver.findElement(By.id("txt_password")).sendKeys(password);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_passwordConfirm"),30);
        driver.findElement(By.id("txt_passwordConfirm")).sendKeys(confrimPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_signUp"),30);
        driver.findElement(By.id("btn_signUp")).click();

        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//div[@role='alert']"),30);
        String alertMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        assert alertMessage.equals("Registration Successful") : alertMessage;

        otpUtils = new OTPUtils();
        String otp = otpUtils.getOTPFromMailosaur(email);

        driver.findElement(By.xpath("//*[contains(@id, 'otp_0_')]")).sendKeys(otp);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_submitOTP"), 30);
        driver.findElement(By.id("btn_submitOTP")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//h1[text()=' Congratulations! Your account is ready to use! ']"), 30);

    }
}
