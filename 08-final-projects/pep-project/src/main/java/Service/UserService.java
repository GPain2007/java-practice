package Service;
import Model.Account;
import DAO.*;
import java.sql.*;
import java.util.*;

public class UserService {
    private List<Account> users;
    private UserDAO userDAO;
    public UserService(){
        this.users = new ArrayList<>();
        this.userDAO = new UserDAO();
    }
    


    public Account login(String username, String password) {
        Account user = userDAO.login(username, password);
        if (user != null) {
            return user; 
        }
        return null; 
    }

   


    public Account createUser(Account account) throws SQLException {
        UserDAO userDAO = new UserDAO();
        return userDAO.createUser(account);
    }
    

    


    public Account getUserByUsername(String username) throws SQLException {
      UserDAO userDAO = new UserDAO();
      return userDAO.getUserByUsername(username);
    }

    public Account getUserById(int account_id){
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(account_id);
       
    }

 



   


   
    
}
