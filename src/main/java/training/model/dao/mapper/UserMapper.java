package training.model.dao.mapper;

import ua.training.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractOne(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public List<User> extractAll(ResultSet rs) throws SQLException {
        return null;
    }
}
