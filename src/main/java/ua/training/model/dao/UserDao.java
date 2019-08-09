package ua.training.model.dao;

import ua.training.model.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends AutoCloseable {
    User findByUsername(String username) throws SQLException;

    User findByUsernameAndPassword(String username, String password) throws SQLException;

    void create(User build) throws SQLException;

    void updateMoney(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findById(long id) throws SQLException;
}
