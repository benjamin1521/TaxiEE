package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private UserMapper mapper = new UserMapper();

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        String query = queries.getString("get.user.by.username");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;

            }
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        String query = queries.getString("get.user.by.username.and.password");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;
            }
        }

    }

    @Override
    public void create(User entity) throws SQLException {
        String query = queries.getString("create.user");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getRole().toString());
            ps.setString(2, entity.getUsername());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getFullNameEn());
            ps.setString(5, entity.getFullNameUa());
            ps.setLong(6, entity.getMoneySpent());
            ps.execute();
        }
    }

    @Override
    public void updateMoney(User user) throws SQLException {
        String query = queries.getString("update.user.money");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, user.getMoneySpent());
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        }
    }


    @Override
    public List<User> findAll() throws SQLException {
        String query = queries.getString("get.all.users");
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            return mapper.extractAll(rs);
        }
    }

    @Override
    public User findById(long id) throws SQLException {
        String query = queries.getString("get.user.by.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;
            }
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
