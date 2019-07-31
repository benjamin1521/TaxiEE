package training.model.dao;

import ua.training.model.dao.impl.JDBCDaoFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract OrderDao createOrderDao(Connection connection);

    public abstract UserDao createUserDao(Connection connection);

    public abstract TaxiDao createTaxiDao(Connection connection);

    public abstract CouponDao createCouponDao(Connection connection);

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

}
