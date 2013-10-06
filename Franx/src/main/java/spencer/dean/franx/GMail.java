package spencer.dean.franx;

import javax.mail.*;

import java.util.Properties;

public class GMail {

    private static final String PROTOCOL = "imaps";
    private static final String HOST = "imap.gmail.com";
    private static final String PORT = "993";
    private static final String USER = "{gmail user}";
    private static final String PASSWORD = "{gmail password}";
    
    private static Properties setProperties() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", PROTOCOL);
        props.setProperty("mail.imaps.host", HOST);
        props.setProperty("mail.imaps.port", PORT);
        return props;
    }

    private static Session getSession() {
        return Session.getDefaultInstance(setProperties(), null);
    }

    private static Store getStore() throws NoSuchProviderException {
        Session session = getSession();
        return session.getStore(PROTOCOL);
    }
    private static Store connect() throws MessagingException {
        Store store = getStore();
        store.connect(HOST, USER, PASSWORD);
        return store;
    }

    private static Folder getFolder(String folder) throws MessagingException {
        Store store = connect();
        return store.getFolder(folder);
    }

    public static Folder[] listFolders() throws MessagingException {
        Store store = connect();
        return store.getDefaultFolder().list("*");
    }

    public static int getMessageCountForINBOX() throws MessagingException {
        Folder inbox = getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        return inbox.getMessageCount();
    }

    public static Message[] listMessagesForINBOX(int start, int end) throws MessagingException {
        Folder inbox = getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        return inbox.getMessages(start, end);
    }
}
