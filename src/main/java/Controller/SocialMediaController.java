package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Service.SocialMediaService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.*;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    static SocialMediaService smService;

    public SocialMediaController() {
        this.smService = new SocialMediaService();
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        // not required but for testing purposes
        app.get("users", SocialMediaController::getAllUsersHandler);
        
        app.post("register", SocialMediaController::registerHandler);
        app.post("login", SocialMediaController::loginHandler);
        app.post("messages", SocialMediaController::messageHandler);
        app.get("messages", SocialMediaController::getAllMsgsHandler);
        app.get("messages/{message_id}", SocialMediaController::getMessageHandler);
        app.delete("messages/{message_id}", SocialMediaController::deleteHandler);
        app.patch("message/{message_id}", SocialMediaController::patchHandler);
        app.get("accounts/{account_id}/messages", SocialMediaController::getAllFromUserHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */ /*
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    */ 

    private static void getAllUsersHandler(Context ctx) {
        List<Account> users = smService.getAllUsers();
        ctx.json(users);
        ctx.status(200);
    }

    private static void registerHandler(Context ctx) {
        String requestBody = ctx.body();
        ObjectMapper om = new ObjectMapper();
        try {
            Account newUser = om.readValue(requestBody, Account.class);
            if (newUser.getUsername().length() == 0 || 
                newUser.getPassword().length() < 4) {
                ctx.status(400);
                System.out.println("Account parameters invalid!");
                return;
            }
            Account savedUser = smService.registerUser(newUser);
            ctx.json(savedUser);
            ctx.status(200);
        } catch (JsonProcessingException | NullPointerException e) {
            ctx.status(400);
        }
    }

    private static void loginHandler(Context ctx) {
        String requestBody = ctx.body();
        ObjectMapper om = new ObjectMapper();
        try {
            Account user = om.readValue(requestBody, Account.class);
            Account foundUser = smService.loginUser(user);
            if (foundUser == null) {
                System.out.println("Login information not found in database!");
                ctx.status(401);
            } else {
                ctx.json(foundUser);
                ctx.status(200);
            }
        } catch (JsonProcessingException e) {
            ctx.status(401);
        }
    }

    private static void messageHandler(Context ctx) {
        String requestBody = ctx.body();
        ObjectMapper om = new ObjectMapper();
        try {
            Message newMessage = om.readValue(requestBody, Message.class);
            if (newMessage.getMessage_text().length() == 0 || 
                newMessage.getMessage_text().length() >= 255) {
                ctx.status(400);
                System.out.println("Message is an invalid size!");
                return;
            }
            Message savedMessage = smService.sendMessage(newMessage);
            ctx.json(savedMessage);
            ctx.status(200);
        } catch (JsonProcessingException | NullPointerException e) {
            ctx.status(400);
        }
    }

    private static void getAllMsgsHandler(Context ctx) {
        List<Message> msgs = smService.getAllMsgs();
        ctx.json(msgs);
        ctx.status(200);
    }

    private static void getMessageHandler(Context ctx) {
        String msgID = ctx.pathParam("message_id");
        Message msg = smService.getMessageByID(Integer.parseInt(msgID));
        if (!(msg == null)) {
            ctx.json(msg);
        }
        ctx.status(200);
    }

    private static void deleteHandler(Context ctx) {
        String msgID = ctx.pathParam("message_id");
        Message msg = smService.deleteMessage(Integer.parseInt(msgID));
        if (!(msg == null)) {
            ctx.json(msg);
        }
        ctx.status(200);
    }

    private static void patchHandler(Context ctx) {
        
    }

    private static void getAllFromUserHandler(Context ctx) {

    }

}