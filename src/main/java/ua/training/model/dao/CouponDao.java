package ua.training.model.dao;

import ua.training.model.entities.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface CouponDao extends AutoCloseable {
    void create(Coupon entity) throws SQLException;

    Coupon findById(long id) throws SQLException;

    List<Coupon> findByIdUserAndOrderNull(long id) throws SQLException;

    Coupon findByOrderId(Long order) throws SQLException;

    void setOrder(Long order, Long coupon) throws SQLException;

    void setOrderNull(Long coupon) throws SQLException;

    void deleteByOrderId(Long id) throws SQLException;
}
