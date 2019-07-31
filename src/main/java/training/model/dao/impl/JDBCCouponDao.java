package training.model.dao.impl;

import ua.training.model.dao.CouponDao;
import ua.training.model.dao.mapper.CouponMapper;

import java.sql.Connection;
import java.util.ResourceBundle;

public class JDBCCouponDao implements CouponDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private CouponMapper mapper = new CouponMapper();

    public JDBCCouponDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {

    }
}
