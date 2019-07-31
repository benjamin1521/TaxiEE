package training.model.dao.mapper;

import ua.training.model.entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractOne(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public List<Order> extractAll(ResultSet rs) throws SQLException {
        return null;
    }
}
