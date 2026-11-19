package Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import Model.*;
import Service.*;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.*;
import java.util.List;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
   UserService userService;
   MessageService messageService;

   public SocialMediaController(){
        this.userService = new UserService();
        this.messageService = new MessageService(); 
   }


   

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getOneMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserIdHandler);
        return app;
    }


    private void registerHandler(Context context) throws JsonMappingException, JsonProcessingException, SQLException {
        
            // Parse the JSON request body into an Account object
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(context.body(), Account.class);
            Account createUser = userService.createUser(account);
            if (account.getUsername().isEmpty() || account.getPassword().length() < 4 ) {
                context.status(400);
                 return;
                 } else if (createUser != null){
                   context.status(200);
                   context.json(mapper.writeValueAsString(createUser));
               } else {
                   context.status(400);
               }
       
    }

   
   
    

    private void loginHandler(Context context) throws JsonProcessingException {
        // Implement login logic here.
        // Verify username and password, return account details if successful.
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loginSuccessful = userService.login(account.getUsername(), account.getPassword());
        if (loginSuccessful != null) {
            context.status(200);
            context.json(mapper.writeValueAsString(loginSuccessful));
        } else {
            context.status(401);
        }
    }

    private void createMessageHandler(Context context) throws JsonProcessingException, SQLException{

        // Implement message creation logic here.
        // Verify message_text and posted_by, create a new message, and return it.
        // Implement message creation logic here
        // Parse request data, create a new message, and return it
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.createMessage(message);
        Account postedByUser = userService.getUserById(message.getPosted_by());
        if (message.getMessage_text() == null ||
            message.getMessage_text().trim().isEmpty() ||
            message.getMessage_text().length() > 254) {
            context.status(400).result("");
            return;
        } else if (postedByUser == null) {
            context.status(400).result("");
            return;
        } else {
            context.status(200); 
            context.json(newMessage);
        }
        
        
        
        
        
    }
    

    private void getAllMessagesHandler(Context context) throws SQLException, JsonMappingException, JsonProcessingException {
        // Implement logic to retrieve all messages from the database and return them.
        List<Message> allMessages = messageService.getAllMessages();
            context.json(allMessages);
    }

  

    private void getOneMessageHandler(Context context) throws SQLException {
        
            int message_id = Integer.parseInt(context.pathParam("message_id"));
            Message singleMessage = messageService.getMessageById(message_id);
            
            if (singleMessage == null){
                context.status(200);
            } else {
                context.status(200);
                context.json(singleMessage);
            }
            
        
    }

    private void deleteMessageHandler(Context ctx) throws JsonMappingException, JsonProcessingException, SQLException {
        
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessage(message_id);
        if (deletedMessage == null){
            ctx.status(200);
        } else{
            ctx.status(200);
            ctx.json(deletedMessage);
        }
        
       
      
    }

    private void updateMessageHandler(Context context) throws SQLException, JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        if (message.getMessage_text() == null ||
            message.getMessage_text().trim().isEmpty() ||
            message.getMessage_text().length() > 254) {
            context.status(400).result("");
            return;
        }
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        String message_text = message.getMessage_text();
    
        Message updatedMessage = messageService.updateMessageText(message_id, message_text);
    
        if (updatedMessage != null) {
            // Message was successfully updated
            context.status(200); // HTTP status code for success
            context.json(updatedMessage); // Return the updated message as JSON
        } else {
            // Error handling for unsuccessful update
            context.status(400); // HTTP status code for internal server error
            
        }
    }

    private void getMessagesByUserIdHandler(Context context) throws SQLException {
        // Implement logic to retrieve messages by account_id and return them.
        int posted_by = Integer.parseInt(context.pathParam("account_id")); 
        List<Message> userMessages = messageService.getMessagesByUserId(posted_by);
        context.status(200);
        context.json(userMessages);
    }
}








    

