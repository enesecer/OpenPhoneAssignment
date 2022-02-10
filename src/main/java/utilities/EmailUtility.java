package utilities;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.util.Properties;

public class EmailUtility {

    final static String USER_EMAIL = ConfigurationReader.getProperty("loginUser");
    final static String USER_PASSWORD = ConfigurationReader.getProperty("password");

    public static String check() {
        try {
            String host = "imap.gmail.com";
            Properties properties = new Properties();

            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "995");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", host);

            Session emailSession = Session.getDefaultInstance(properties);

            // create the imap store object and connect to the imap server
            Store store = emailSession.getStore("imaps");

            store.connect(host, USER_EMAIL, USER_PASSWORD);

            // create the inbox object and open it
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            System.out.println("messages.length---" + messages.length);
            String verificationCode;
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                MimeMultipart multipart = (MimeMultipart) message.getContent();
                System.out.println("This message consists of " + multipart.getCount() + " parts");
                MimeBodyPart bodyPart = (MimeBodyPart) multipart.getBodyPart(i);

                message.setFlag(Flags.Flag.SEEN, true);
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                String subject = message.getSubject();
                System.out.println("Subject: " + subject);
                String content = bodyPart.getContent().toString();
                if (subject.equalsIgnoreCase("Your OpenPhone Code")) {
                    verificationCode = content.replaceAll("[^0-9]", "");
                    System.out.println("Verification code captured: " + verificationCode);
                    return verificationCode;
                }
            }
            inbox.close(false);
            store.close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
