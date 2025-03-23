package org.financePro.pages;

import com.mailosaur.MailosaurException;
import org.financePro.utils.GlobalConfigurations;
import org.financePro.utils.OTPUtils;
import org.financePro.utils.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ForgetPassword {
    WebDriver driver = GlobalConfigurations.getInstance().getDriver();
    OTPUtils otpUtils;
    PropertyFileReader prop = new PropertyFileReader();
    String forgetPWLinkElement = prop.getProperty("forgetPasswordPageObjects","forget.password.link.element");
    String emailElement = prop.getProperty("forgetPasswordPageObjects","email.element");
    String resetButtonElement = prop.getProperty("forgetPasswordPageObjects","reset.button.element");
    String otpFieldElement = prop.getProperty("forgetPasswordPageObjects","otp.field.element");
    String otpSubmitButtonElement = prop.getProperty("forgetPasswordPageObjects","otp.submit.button.element");

    public void forgetPassword(String email, String password,String optSentSuccessMessage, String PasswordResetSuccessMsg) throws MailosaurException, IOException {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(forgetPWLinkElement),30);
        driver.findElement(By.xpath(forgetPWLinkElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(emailElement),30);
        driver.findElement(By.id(emailElement)).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(resetButtonElement),30);
        driver.findElement(By.id(resetButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(optSentSuccessMessage);

        otpUtils = new OTPUtils();
        String otp = otpUtils.getOTPFromMailosaur(email);

        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(otpFieldElement),30);
        driver.findElement(By.xpath(otpFieldElement)).sendKeys(otp);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(otpSubmitButtonElement),30);
        driver.findElement(By.xpath(otpSubmitButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(PasswordResetSuccessMsg);
    }
}
