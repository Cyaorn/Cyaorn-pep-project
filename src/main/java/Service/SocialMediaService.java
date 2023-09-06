package Service;

import DAO.SocialMediaDAO;
import Model.*;
import java.util.List;

public class SocialMediaService {

    SocialMediaDAO smDAO;
    public SocialMediaService() {
        try {
            this.smDAO = new SocialMediaDAO();
        } catch(Exception e) {
            System.out.println("database not found or something");
            System.exit(0);
        } 
    }

    public List<Account> getAllUsers() {
        return smDAO.getAllUsers();
    }

    public Account registerUser(Account newUser) {
        // if username already exists, return null Account since registration failed
        if (smDAO.getAccountByUser(newUser.getUsername()) != null) {
            System.out.println("Username already taken!");
            return null;
        }
        // want to register a new user, and use the newly-generated ID to retrieve the new account data
        String newUsername = smDAO.registerUser(newUser);
        Account savedUser = smDAO.getAccountByUser(newUsername);
        return savedUser;
    }

    public Account loginUser(Account user) {
        return smDAO.getAccountByUserPass(user);
    }
    
    public Message sendMessage(Message newMsg) {
        int newID = smDAO.sendMessage(newMsg);
        Message savedMsg = smDAO.getMessageByID(newID);
        return savedMsg;
    }

    public List<Message> getAllMsgs() {
        return smDAO.getAllMsgs();
    }

    public Message getMessageByID(int id) {
        return smDAO.getMessageByID(id);
    }

    // want to return the message that was deleted
    public Message deleteMessage(int id) {
        Message oldMsg = smDAO.getMessageByID(id);
        smDAO.deleteMessage(id);
        return oldMsg;
    }

    public Message updateMessage(int id, Message newMsg) {
        int newID = smDAO.updateMessage(id, newMsg); // will always just be the same ID but whatever
        return smDAO.getMessageByID(newID);
    }

    public List<Message> getAllMsgsFromUser(int id) {
        return smDAO.getAllMsgsFromUser(id);
    }
}
