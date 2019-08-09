package ua.training.model.dao.impl;

import ua.training.model.dao.OrderDao;
import ua.training.model.dao.mapper.OrderMapper;
import ua.training.model.entities.Order;
import ua.training.model.entities.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private OrderMapper mapper = new OrderMapper();

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order entity) throws SQLException {
        String query = queries.getString("create.order");
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, entity.getUser().getId());
            ps.setLong(2, entity.getTaxi().getId());
            ps.setString(3, entity.getType().name());
            ps.setString(4, entity.getStartStreet().name());
            ps.setLong(5, entity.getStartHouse());
            ps.setString(6, entity.getEndStreet().name());
            ps.setLong(7, entity.getEndHouse());
            ps.setLong(8, entity.getCost());
            ps.setLong(9, entity.getDistance());
            ps.setDouble(10, entity.getWaitingTime());
            ps.setDouble(11, entity.getDrivingTime());
            ps.setObject(12, Timestamp.valueOf(entity.getOrderDate()));
            ps.setString(13, entity.getStatus().name());
            ps.execute();
            try (ResultSet generatedKey = ps.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    entity.setId(generatedKey.getLong(1));
                }
            }
        }
    }

    @Override
    public Order findById(long id) throws SQLException {
        String query = queries.getString("get.order.by.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;
            }
        }
    }

    @Override
    public int getCount(Long id) throws SQLException {
        String query = queries.getString("get.orders.count");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    @Override
    public int getCount(Long id, String status) throws SQLException {
        String query = queries.getString("get.orders.count.by.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.setString(2, status);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    @Override
    public List<Order> getPage(Long id, int offset, int limit) throws SQLException {
        String query = queries.getString("get.orders.page");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        }
    }


    @Override
    public List<Order> getPage(Long id, int offset, int limit, String status) throws SQLException {
        String query = queries.getString("get.orders.page.by.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.setString(2, status);
            ps.setInt(3, offset);
            ps.setInt(4, limit);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        }
    }

    @Override
    public void delete(Long order) throws SQLException {
        String query = queries.getString("delete.order");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, order);
            ps.executeUpdate();
        }
    }

    @Override
    public void updateStatus(Long id, Status status) throws SQLException {
        String query = queries.getString("update.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status.name());
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void updateTaxiStatus(Long id) throws SQLException {
        String query = queries.getString("update.taxi.status");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, Status.Unavailable.name());
            ps.setString(2, Status.Active.name());
            ps.setLong(3, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
