package customerManagementBackend.service;

import customerManagementBackend.entity.User;

public interface UserService {
    void save(User user);
    User getUser(String email);
    User loginUser(User user);
}
