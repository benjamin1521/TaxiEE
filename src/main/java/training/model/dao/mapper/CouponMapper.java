package training.model.dao.mapper;

import ua.training.model.entities.Coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CouponMapper implements ObjectMapper<Coupon> {
    @Override
    public Coupon extractOne(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public List<Coupon> extractAll(ResultSet rs) throws SQLException {
        return null;
    }
}
