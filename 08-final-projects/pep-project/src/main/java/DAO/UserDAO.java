package DAO;
import Model.*;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection = ConnectionUtil.getConnection();

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    public UserDAO() {
    }

    public Account createUser(Account account) throws SQLException {
        
        try{
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedAccountId = (int) generatedKeys.getLong(1);
                return new Account(generatedAccountId, account.getUsername(), account.getPassword());
            } 
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
         // If something went wrong, return null or throw an exception
        return null;
        }
    
    
    
            

    public Account getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM account WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int accountId = resultSet.getInt("account_id");
                    String password = resultSet.getString("password");
                    return new Account(accountId, username, password);
                }
            }
        }
        return null; 
    }
    



    public Account getUserById(int account_id){
        try{
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Account user = new Account();
                user.setAccount_id(resultSet.getInt("account_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account login(String username, String password) {
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                int account_id = resultSet.getInt("account_id");
                String storedUsername = resultSet.getString("username");
                String storedPassword = resultSet.getString("password");
                Account account = new Account(account_id, storedUsername, storedPassword);
                return account; 
            }
                   
        } catch(SQLException e) {
            e.printStackTrace();
        }
    
        return null; 
    }
    
    
    
    
    
    


}
