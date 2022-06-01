package web.DAO;

import org.springframework.stereotype.Component;
import web.models.User;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserDAO {

    private static int USER_COUNT;
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USER_COUNT, "UserName1"));
        users.add(new User(++USER_COUNT, "UserName2"));
        users.add(new User(++USER_COUNT, "UserName3"));
        users.add(new User(++USER_COUNT, "UserName4"));
        users.add(new User(++USER_COUNT, "UserName5"));
    }

    public List<User> index() {
        return users;
    }

    public User showUser(int id) {
        return  users.stream().filter(user -> user.getId() == id).findAny().orElse(null);}

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(int id, User updateUser) {
        User userToBeUpdate = showUser(id);
        userToBeUpdate.setName(updateUser.getName());
    }

    public void delete(int id) {
        users.removeIf(p -> p.getId() == id);
    }
}
