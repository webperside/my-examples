import service.MailService;

public class Main {

    public static void main(String[] args) {
        MailService mailService = new MailService();

        String protocol = "imap";
        String host = "imap.gmail.com"; // host depends on the server
        String port = "993"; // port depends on the server


        String userName = "your_email";
        String password = "your_password";

        mailService.downloadEmails(protocol, host, port, userName,password);
    }
}
