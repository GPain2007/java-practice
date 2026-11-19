package Service;
import DAO.*;
import Model.*;
import java.sql.*;
import java.util.*;


public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService( MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    public Message createMessage(Message message) throws SQLException {
       return messageDAO.createMessage(message);
       
    }

    public List<Message> getAllMessages() throws SQLException {
        List<Message> messages = messageDAO.getAllMessages();
        return messages;
    }

    public Message getMessageById(int message_id) throws SQLException {
        return messageDAO.getMessageById(message_id);
    }

    public Message updateMessageText(int message_id, String message_text) throws SQLException{
        return messageDAO.updateMessageText(message_id, message_text);
        }

    public Message deleteMessage(int message_id) throws SQLException {
        // Use the MessageDAO to delete the message and propagate any SQLException
       
            // Use the MessageDAO to delete the message
            return messageDAO.deleteMessage(message_id);
        
    }


    public List<Message> getMessagesByUserId(int posted_by) throws SQLException {
        return messageDAO.getMessagesByUserId(posted_by);
    }

}