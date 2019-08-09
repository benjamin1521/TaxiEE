package ua.training.model.service;

import ua.training.model.dao.CouponDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TaxiDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();
    private ConnectionPoolHolder connectionPool = ConnectionPoolHolder.getInstance();

    public boolean createUser(User user) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);
            connection.setAutoCommit(false);

            User userFromDb = userDao.findByUsername(user.getUsername());
            if (userFromDb != null) {
                return false;
            }
            userDao.create(user);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public User getUser(String username, String password) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findByUsernameAndPassword(username, password);

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean createTaxi(Taxi taxi) {
        try (Connection connection = connectionPool.getConnection()) {
            TaxiDao taxiDao = daoFactory.createTaxiDao(connection);

            taxiDao.create(taxi);

            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean createCoupon(Coupon coupon) {
        try (Connection connection = connectionPool.getConnection()) {
            CouponDao couponDao = daoFactory.createCouponDao(connection);

            couponDao.create(coupon);

            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
    public List<User> getAllUsers() {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findAll();

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public User findById(long id) {
        try (Connection connection = connectionPool.getConnection()) {
            UserDao userDao = daoFactory.createUserDao(connection);

            return userDao.findById(id);

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
