package ua.training.model.dao.mapper;

import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractOne(ResultSet rs) throws SQLException {
        return User.newBuilder()
                .id(rs.getLong("id"))
                .fullNameUa(rs.getString("full_name_ua"))
                .fullNameEn(rs.getString("full_name_en"))
                .password(rs.getString("password"))
                .username(rs.getString("username"))
                .moneySpent(rs.getLong("money_spent"))
                .role(Role.valueOf(rs.getString("role")))
                .build();
    }

    @Override
    public List<User> extractAll(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extractOne(rs));
        }
        return result;
    }
}
