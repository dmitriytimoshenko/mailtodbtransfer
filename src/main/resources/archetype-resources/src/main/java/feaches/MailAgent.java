package feaches;


import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Deprecated
public class MailAgent {

    static final Logger logger = Logger.getLogger(MailAgent.class);

    final String LOGIN = "asassinich@gmail.com";
    final String PASSWORD = "1shooter1254";


    public String getMail(int index) {


        try {
            logger.info("Попытка подключения к gmail...");

            Properties props = System.getProperties();
            props.setProperty("mail.host", "imap.gmail.com");
            props.setProperty("mail.imap.port", "993");
            props.setProperty("mail.store.protocol", "imaps");
//            props.setProperty("mail.debug", "true");
            props.setProperty("mail.imaps.ssl.trust", "imap.gmail.com");


            logger.info("Получение сессии...");
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(LOGIN,PASSWORD);
                }
            });
            logger.info("Успех!");
            logger.info(session);

            logger.info("Получаем хранилище...");
            Store store = session.getStore();
            store.connect();
            if (store.isConnected()) {
                logger.info("Успех!");
            }
/*            Folder[] folders = store.getDefaultFolder().list();


            for (Folder fd: folders)
            {
                logger.info(fd.getName());
            }

            Folder[] subfolders = store.getFolder("[Gmail]").list();


            for (Folder fd: subfolders)
            {
                logger.info(fd.getName());
            }
*/

            Folder spamFolder = store.getFolder("[Gmail]").getFolder("Спам");
            spamFolder.open(Folder.READ_WRITE);
            Message[] messages = spamFolder.getMessages();



            for (Message ms: messages)
            {
                InternetAddress[] internetAddresses = (InternetAddress[]) ms.getFrom();

                logger.info(ms.getSentDate());
                logger.info(ms.getReplyTo());
                for (InternetAddress ia: internetAddresses) {
                    logger.info(ia.getAddress());
                    logger.info(ia.getPersonal());

                }


                logger.info(ms.getContentType());

                if (ms.isMimeType("multipart/*")) {

                    MimeMultipart mimeMultipart = (MimeMultipart) ms.getContent();
                    for (int i = 0; i < mimeMultipart.getCount(); i++) {
                        logger.info(mimeMultipart.getBodyPart(i).getContent());
                    }
                } else if (ms.isMimeType("text/*")) {
                    logger.info(ms.getContent());
                }

                logger.info(">>>");
                logger.info(">>>");
                logger.info(">>>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
