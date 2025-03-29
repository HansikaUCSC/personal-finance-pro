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
    String emailSubject = prop.getProperty("forgetPasswordPageObjects","email.subject");
    String passwordElement = prop.getProperty("forgetPasswordPageObjects","password.element");
    String confirmPasswordElement = prop.getProperty("forgetPasswordPageObjects","confirm.password.element");
    String updatePasswordButtonElement = prop.getProperty("forgetPasswordPageObjects","update.password.button.element");

    public void forgetPassword(String email, String password,String confirmPassword,String optSentSuccessMessage, String OTPValidatedSuccessMsg,String PasswordResetSuccessMsg) throws MailosaurException, IOException {
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(forgetPWLinkElement),30);
        driver.findElement(By.xpath(forgetPWLinkElement)).click();
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(emailElement),30);
        driver.findElement(By.id(emailElement)).sendKeys(email);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(resetButtonElement),30);
        driver.findElement(By.id(resetButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(optSentSuccessMessage);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(otpFieldElement),30);

        otpUtils = new OTPUtils();
        String otp = otpUtils.getOTPFromMailosaur(email,emailSubject);

        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(otpFieldElement),30);
        driver.findElement(By.xpath(otpFieldElement)).sendKeys(otp);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(otpSubmitButtonElement),30);
        driver.findElement(By.id(otpSubmitButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(OTPValidatedSuccessMsg);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(passwordElement),30);
        driver.findElement(By.xpath(passwordElement)).sendKeys(password);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.xpath(confirmPasswordElement),30);
        driver.findElement(By.xpath(confirmPasswordElement)).sendKeys(confirmPassword);
        GlobalConfigurations.getInstance().waitUntilNextElementAppears(By.id(updatePasswordButtonElement),30);
        driver.findElement(By.id(updatePasswordButtonElement)).click();
        GlobalConfigurations.getInstance().verifyAlert(PasswordResetSuccessMsg);
    }
}
