package ua.training.model.dao.impl;

import ua.training.model.dao.CouponDao;
import ua.training.model.dao.mapper.CouponMapper;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCCouponDao implements CouponDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private CouponMapper mapper = new CouponMapper();

    public JDBCCouponDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Coupon entity) throws SQLException {
        String query = queries.getString("create.coupon");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, entity.getUser().getId());
            ps.setDouble(2, entity.getDiscountPercent());
            ps.execute();
        }
    }

    @Override
    public List<Coupon> findByIdUserAndOrderNull(long id) throws SQLException {
        String query = queries.getString("get.coupons.free");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        }
    }

    @Override
    public Coupon findById(long id) throws SQLException {
        String query = queries.getString("get.coupon.by.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;
            }
        }
    }

    @Override
    public Coupon findByOrderId(Long order) throws SQLException {
        String query = queries.getString("get.coupon.by.order.id");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, order);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractOne(rs) : null;
            }
        }
    }

    @Override
    public void setOrder(Long order, Long coupon) throws SQLException {
        String query = queries.getString("update.coupon.order");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, order);
            ps.setLong(2, coupon);
            ps.executeUpdate();
        }
    }

    @Override
    public void setOrderNull(Long coupon) throws SQLException {
        String query = queries.getString("update.coupon.order.null");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, coupon);
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteByOrderId(Long id) throws SQLException {
        String query = queries.getString("delete.coupon");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
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
