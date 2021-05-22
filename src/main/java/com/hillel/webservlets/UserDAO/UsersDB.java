package com.hillel.webservlets.UserDAO;

import com.hillel.webservlets.controller.Role;
import com.hillel.webservlets.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class UsersDB {

    private static UsersDB instance;

    private final Map<String, User> inMemoryDB;

    public static UsersDB getInstance() {
        if (instance == null) {
            instance = new UsersDB();
        }
        return instance;
    }

    private UsersDB() {
        inMemoryDB = new HashMap<>();
        inMemoryDB.put("admin", new User("admin", "admin", Role.ROLE_ADMIN));
        inMemoryDB.put("support", new User("support", "support", Role.ROLE_SUPPORT));
        inMemoryDB.put("user", new User("user", "user", Role.ROLE_USER));
    }

    public void add(User user) {
        String login = user.getLogin();
        inMemoryDB.put(login, user);
    }

    public void removeUser(String login) {
        inMemoryDB.remove(login);
    }

    public Optional<User> getUserByCredentials(String login, String password) {
        return inMemoryDB.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findAny();
    }

    public Optional<Role> getRole(String login) {
        return inMemoryDB.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(user -> user.getLogin().equals(login))
                .map(User::getRole)
                .findFirst();
    }
}
