package Service;

import DAO.SocialMediaDAO;
import Model.*;

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

    public Account registerUser() {
        return null; // placeholder 
    }
    
}
