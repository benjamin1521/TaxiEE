package ua.training.model.dao.mapper;

import ua.training.model.entities.Coupon;
import ua.training.model.entities.Order;
import ua.training.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponMapper implements ObjectMapper<Coupon> {
    @Override
    public Coupon extractOne(ResultSet rs) throws SQLException {
        return Coupon.newBuilder()
                .id(rs.getLong("id"))
                .discountPercent(rs.getDouble("discount_percent"))
                .order(Order.newBuilder()
                        .id(rs.getLong("id_order"))
                        .build())
                .user(User.newBuilder()
                        .id(rs.getLong("id_user"))
                        .build())
                .build();
    }

    @Override
    public List<Coupon> extractAll(ResultSet rs) throws SQLException {
        List<Coupon> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extractOne(rs));
        }
        return result;
    }
}
