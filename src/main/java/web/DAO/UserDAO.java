package web.DAO;

import org.springframework.stereotype.Component;
import web.models.User;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserDAO {
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(1, "UserName1"));
        users.add(new User(2, "UserName2"));
        users.add(new User(3, "UserName3"));
        users.add(new User(4, "UserName4"));
        users.add(new User(5, "UserName5"));
    }

    public List<User> index() {
        return users;
    }

    public User showUser(int id) {
        return  users.stream().filter(user -> user.getId() == id).findAny().orElse(null);}

}
