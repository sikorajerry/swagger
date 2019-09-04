package cz.praha.jsikora.mock;

import cz.praha.jsikora.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMock {
    private final List<User> userList = new ArrayList<User>();

    @PostConstruct
    public void init() {
        User  user = new User();
        user.setId(0l);
        user.setName("Predak Trombonek");
        userList.add(user);
        user = new User();
        user.setId(1l);
        user.setName("Adam Kaleta");
        userList.add(user);
        user = new User();
        user.setId(2l);
        user.setName("Beata Suszka");
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
