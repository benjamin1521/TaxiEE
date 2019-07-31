package training.model.dao.impl;

import ua.training.model.dao.CouponDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.dao.TaxiDao;
import ua.training.model.dao.UserDao;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {


    @Override
    public OrderDao createOrderDao(Connection connection) {
        return new JDBCOrderDao(connection);
    }

    @Override
    public UserDao createUserDao(Connection connection) {
        return new JDBCUserDao(connection);
    }

    @Override
    public TaxiDao createTaxiDao(Connection connection) {
        return new JDBCTaxiDao(connection);
    }

    @Override
    public CouponDao createCouponDao(Connection connection) {
        return new JDBCCouponDao(connection);
    }


}
