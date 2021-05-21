package service;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.util.BASE64DecoderStream;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.Properties;

public class MailService {

    private Properties getServerProperties(String protocol, String host, String port) {
        Properties properties = new Properties();

        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        // SSL setting
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));

        return properties;
    }

    public void downloadEmails(String protocol, String host, String port,
                               String userName, String password) {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            IMAPFolder imapFolder = (IMAPFolder) folderInbox;
            folderInbox.open(Folder.READ_ONLY);

            // fetches new messages from server
//            Message[] messages = folderInbox.getMessages();
            Message[] messages = imapFolder.getMessages();

            for (int i = 0; i < 10; i++) {
                Message msg = messages[i];
                System.out.println(imapFolder.getUID(msg));
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(Message.RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(Message.RecipientType.CC));
                String sentDate = msg.getSentDate().toString();

                String contentType = msg.getContentType();
                StringBuilder messageContent = new StringBuilder();

                if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = new StringBuilder(content.toString());
                        }
                    } catch (Exception ex) {
                        messageContent = new StringBuilder("[Error downloading content]");
                        ex.printStackTrace();
                    }
                }
                else if(contentType.contains("multipart")){
                    Multipart multipart = (Multipart) msg.getContent();
                    for(int j = 0 ; j < multipart.getCount() ; j++){
                        MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(j);
                        Object obj = part.getContent();

                        if(obj instanceof BASE64DecoderStream){
                            messageContent.append("FileName ").append(part.getFileName()).append(" ");
                        }
                        else if(obj instanceof String){
//                            System.out.println(obj.getClass().getName());
                            messageContent.append(obj);
                        }
                    }
                }

                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);
            }

            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseAddresses(Address[] address) {
        StringBuilder listAddress = new StringBuilder();

        if (address != null) {
            for (Address value : address) {
                listAddress.append(value.toString()).append(", ");
            }
        }
        if (listAddress.length() > 1) {
            listAddress = new StringBuilder(listAddress.substring(0, listAddress.length() - 2));
        }

        return listAddress.toString();
    }

}
