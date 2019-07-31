package training.model.dao.impl;

import ua.training.model.dao.OrderDao;
import ua.training.model.dao.mapper.OrderMapper;

import java.sql.Connection;
import java.util.ResourceBundle;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private OrderMapper mapper = new OrderMapper();

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {

    }
}
