package org.financePro;

import com.mailosaur.MailosaurException;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.OTPUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ForgetPassword {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    OTPUtils otpUtils;

    public void forgetPassword(String email, String password) throws MailosaurException, IOException {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//a[@href='/forgotpassword']"),30);
        driver.findElement(By.xpath("//a[@href='/forgotpassword']")).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("txt_emailAddress"),30);
        driver.findElement(By.id("txt_emailAddress")).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id("btn_resetPassword"),30);
        driver.findElement(By.id("btn_resetPassword")).click();

        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//div[@role='alert']"),30);
        String alertMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        assert alertMessage.equals("OTP sent successfully: Activation OTP email sent successfully.") : alertMessage;

        otpUtils = new OTPUtils();
        String otp = otpUtils.getOTPFromMailosaur(email);

        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//*[contains(@id, 'otp_0_')]"),30);
        driver.findElement(By.xpath("//*[contains(@id, 'otp_0_')]")).sendKeys(otp);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//button[text()=' Submit OTP ']"),30);
        driver.findElement(By.xpath("//button[text()=' Submit OTP ']")).click();

        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath("//div[@role='alert']"),30);
        String alertMessageFP = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        assert alertMessageFP.equals("OTP sent successfully: Activation OTP email sent successfully.") : alertMessageFP;
    }
}
