package SenderEmail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by LENOVO on 16/11/2017.
 */

public class SMTPAuthenticator extends Authenticator {

    public SMTPAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "mybirdsilobur@gmail.com";
        String password = "mybird1234";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}
