package spencer.dean.franx;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import spencer.dean.franx.GMail;

public class GMailTest {

    @BeforeClass
    public void setup() {
        //
    }

    @AfterClass
    public void teardown() {
        //
    }

    @Test()
    public void getAllFolders() throws MessagingException {
        Folder[] folders = GMail.listFolders();
        Assert.assertTrue(folders.length > 0);
        for (Folder folder : folders) {
            System.out.println("name: " + folder.getName());
        }
    }
    
    @Test()
    public void getMessageCountForINBOX() throws MessagingException {
        int count = GMail.getMessageCountForINBOX();
        Assert.assertTrue(count > 0);
        System.out.println(count);
    }
    
    @Test()
    public void getTenLatestMessagesForINBOX() throws MessagingException {
        int count = GMail.getMessageCountForINBOX();
        Message[] messages = GMail.listMessagesForINBOX(count-9, count);
        Assert.assertTrue(messages.length > 0);
        for (Message message : messages) {
            System.out.print("date: " + message.getSentDate());
            System.out.println(" >> subject: " + message.getSubject());
            
        }
    }
}