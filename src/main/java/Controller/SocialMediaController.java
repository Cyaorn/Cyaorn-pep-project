package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("register", SocialMediaController::registerHandler);
        app.post("login", SocialMediaController::loginHandler);
        app.post("messages", SocialMediaController::messageHandler);
        app.get("messages", SocialMediaController::getAllHandler);
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

    private static void registerHandler(Context ctx) {

    }

    private static void loginHandler(Context ctx) {

    }

    private static void messageHandler(Context ctx) {

    }

    private static void getAllHandler(Context ctx) {

    }

    private static void getMessageHandler(Context ctx) {

    }

    private static void deleteHandler(Context ctx) {
        
    }

    private static void patchHandler(Context ctx) {
        
    }

    private static void getAllFromUserHandler(Context ctx) {

    }

}