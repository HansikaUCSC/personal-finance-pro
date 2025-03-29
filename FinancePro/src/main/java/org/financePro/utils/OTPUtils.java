package org.financePro.utils;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OTPUtils {

    String apiKey = "CEsScY9wQyRZjRFkVBNQSdWBzY5vLJ67";
    String serverId = "hxd6i8zi";

    public String getOTPFromMailosaur(String email, String emailSubject) throws MailosaurException, IOException {

        MailosaurClient mailosaur = new MailosaurClient(apiKey);

        MessageSearchParams params = new MessageSearchParams();
        params.withServer(serverId);

        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentTo(email).withSentFrom("hasaranga.prasad1234@gmail.com").withSubject(emailSubject);

        Message message = mailosaur.messages().get(params, criteria);

        String HTTPBody = message.html().body();

        Document document = Jsoup.parse(HTTPBody);
        String emailBody = document.text();
        Pattern pattern = null;
        if (emailSubject.equals("Account activation")) {
            pattern = Pattern.compile("activation code to activate your account:\\s*(\\d{6})");
        } else if (emailSubject.equals("OTP Verification")) {
            pattern = Pattern.compile("One-Time Password \\(OTP\\) to proceed:\\s*(\\d{6})");
        }
        Matcher matcher = pattern.matcher(emailBody);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalStateException("No OTP found in the email body.");
        }
    }

}