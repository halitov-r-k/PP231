package web.DAO;

import org.springframework.stereotype.Component;
import web.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserDAO {

    private static int USER_COUNT;
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public User showUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  users WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO  users VALUES (1, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     public void update(int id, User updateUser) {
         try {
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET  name = ? WHERE id=?");
             preparedStatement.setString(1, updateUser.getName());
             preparedStatement.setInt(2, id);
             preparedStatement.executeUpdate();

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
