package customerManagementBackend.dao;

import customerManagementBackend.entity.User;

public interface UserDao {
    void save(User user);
    User getUser(String username);
    User loginUser(User user);
}
