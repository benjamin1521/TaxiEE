package ua.training.model.dao;

import ua.training.model.entities.Order;
import ua.training.model.entities.enums.Status;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends AutoCloseable {
    void create(Order entity) throws SQLException;

    Order findById(long id) throws SQLException;

    int getCount(Long id) throws SQLException;

    int getCount(Long id, String status) throws SQLException;

    List<Order> getPage(Long id, int offset, int limit) throws SQLException;

    List<Order> getPage(Long id, int offset, int limit, String status) throws SQLException;

    void delete(Long order) throws SQLException;

    void updateStatus(Long id, Status status)throws SQLException;

    void updateTaxiStatus(Long id) throws SQLException;
}
