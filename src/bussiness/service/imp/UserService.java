package bussiness.service.imp;

import bussiness.config.IOFile;
import bussiness.entity.User;
import bussiness.service.IUserService;
import bussiness.util.BCrypt;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {
    List<User> users;

    @Override
    public List<User> findUserByName(String name) {
        return users.stream().filter(user -> user.getFullName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public UserService() {
        this.users = IOFile.readFromFile(IOFile.USER_PATH);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    @Override
    public long getNewId() {
        return users.stream().map(User::getUserId).max(Comparator.naturalOrder()).orElse(0L) + 1;
    }

    @Override
    public boolean save(User user) {
        if (findById(user.getUserId()) != null) {
            // User isExist
            users.set(users.indexOf(findById(user.getUserId())), user);
        } else {
            // Add new account
            users.add(user);
        }
        //Add to file
        IOFile.writeToFile(IOFile.USER_PATH,users);
        return true;
    }

    @Override
    public void delete(Long id) {
        users.remove(findById(id));
        //Update in file
        IOFile.writeToFile(IOFile.USER_PATH,users);
    }

    @Override
    public User login(String username, String password) {
        return users.stream()
//                .filter(user -> (user.getUsername().equals(username) || user.getEmail().equals(username)) && user.getPassword().equals(password))
                .filter(user -> (user.getUsername().equals(username) || user.getEmail().equals(username)) && BCrypt.checkpw(password, user.getPassword()))
                .findFirst().orElse(null);
    }
}
