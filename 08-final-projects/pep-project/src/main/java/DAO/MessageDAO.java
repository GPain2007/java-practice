package DAO;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    
    public Message createMessage(Message message) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        
        try {
            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES ( ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedAccountId = (int) generatedKeys.getLong(1);
                return new Message(generatedAccountId, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageList = new ArrayList<>();
        
        try  {
            String sql = "SELECT * FROM Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Message messages = new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"), resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
               messageList.add(messages);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messageList;
    }

    public Message getMessageById(int message_id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM Message WHERE message_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, message_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int posted_by = resultSet.getInt("posted_by");
                    String message_text = resultSet.getString("message_text");
                    long time_posted_epoch = resultSet.getLong("time_posted_epoch");
                    return new Message(message_id, posted_by, message_text, time_posted_epoch);
                }
            }
        }
        return null; 
    }
    
    public Message updateMessageText(int message_id, String message_text) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "UPDATE Message SET message_text = ? WHERE message_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, message_text);
            preparedStatement.setInt(2, message_id);
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected == 1) {
                // The update was successful, so return the updated Message object
                return getMessageById(message_id); // You need to implement this method
            } else {
                // Return null or some other indicator of failure
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed and return null or another failure indicator
            return null;
        }
    }

    public Message deleteMessage(int message_id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String selectQuery = "SELECT * FROM Message WHERE message_id = ?";
        
        // Check if the message exists and fetch it
        try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, message_id);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int messageId = resultSet.getInt("message_id");
                int postedBy = resultSet.getInt("posted_by");
                String messageText = resultSet.getString("message_text");
                long timePostedEpoch = resultSet.getLong("time_posted_epoch");

                // Now, delete the message from the database
                String deleteQuery = "DELETE FROM Message WHERE message_id = ?";
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                    deleteStatement.setInt(1, message_id);
                    int rowsAffected = deleteStatement.executeUpdate();

                    // Check if the message was successfully deleted
                    if (rowsAffected > 0) {
                        return new Message(messageId, postedBy, messageText, timePostedEpoch);
                    }
                }
            }
        }

        return null; // Return null if the message doesn't exist or an error occurs
    }


    public List<Message> getMessagesByUserId(int posted_by) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> userMessages = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM Message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, posted_by);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message messages = new Message(resultSet.getInt("message_id"), resultSet.getInt("posted_by"), resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
                userMessages.add(messages);
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return userMessages;
    }
}







