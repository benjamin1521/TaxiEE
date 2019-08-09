package ua.training.model.service;

import ua.training.model.dao.CouponDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.dao.TaxiDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.dto.PaginationOrders;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.Order;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.exceptions.SQLRuntimeException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static ua.training.model.service.Formulas.calculateDistance;
import static ua.training.model.service.Formulas.calculatePrice;

public class OrderService {
    private DaoFactory daoFactory = JDBCDaoFactory.getInstance();
    private ConnectionPoolHolder connectionPool = ConnectionPoolHolder.getInstance();

    public PaginationOrders getPage(User user, int page, int amount, String status) {
        try (Connection connection = connectionPool.getConnection()) {
            OrderDao dao = daoFactory.createOrderDao(connection);
            connection.setAutoCommit(false);

            PaginationOrders result = new PaginationOrders();

            result.setTotal(status.equals("All")
                    ? dao.getCount(user.getId())
                    : dao.getCount(user.getId(), status));

            if (result.getTotal() <= amount * (page - 1)) {
                connection.commit();
                return result;
            }
            int offset = (page - 1) * amount;
            int limit = Math.min(result.getTotal() - offset, amount);

            result.setList(status.equals("All")
                    ? dao.getPage(user.getId(), offset, limit)
                    : dao.getPage(user.getId(), offset, limit, status));

            result.setFirstNumber(offset + 1);
            result.setLastNumber(offset + limit);

            connection.commit();
            return result;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean createOrder(User user, Order order) {
        try (Connection connection = connectionPool.getConnection()) {
            OrderDao orderDao = daoFactory.createOrderDao(connection);
            CouponDao couponDao = daoFactory.createCouponDao(connection);
            TaxiDao taxiDao = daoFactory.createTaxiDao(connection);
            connection.setAutoCommit(false);

            List<Taxi> taxiList = taxiDao.findByTypeAndBusy(order.getType());
            if (taxiList.isEmpty()) {
                return false;
            }
            Taxi taxi = taxiList.get(new Random().nextInt(taxiList.size()));

            int distance = calculateDistance(order.getStartHouse(), order.getEndHouse(), order.getStartStreet().getCordinate(), order.getEndStreet().getCordinate());
            int waitingDistance = calculateDistance(order.getStartHouse(), taxi.getLocationHouse(), order.getStartStreet().getCordinate(), taxi.getLocationStreet().getCordinate());

            orderDao.create(order.builder()
                    .distance(distance)
                    .Taxi(taxi)
                    .User(user)
                    .orderDate(LocalDateTime.now())
                    .status(Status.Active)
                    .waitingTime(((double) waitingDistance) / order.getType().getSpeed())
                    .drivingTime(((double) distance) / order.getType().getSpeed())
                    .cost(calculatePrice(user.getMoneySpent(),
                            order.getType().getPrice(),
                            distance,
                            couponDao.findById(order.getIdCoupon())))
                    .build());

            if (order.getIdCoupon() > 0) {
                couponDao.setOrder(order.getId(), order.getIdCoupon());
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public Order findById(Long id) {
        try (Connection connection = connectionPool.getConnection()) {
            OrderDao orderDao = daoFactory.createOrderDao(connection);

            return orderDao.findById(id);

        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    public boolean deleteOrder(Long order) {
        try (Connection connection = connectionPool.getConnection()) {
            CouponDao couponDao = daoFactory.createCouponDao(connection);
            OrderDao orderDao = daoFactory.createOrderDao(connection);
            connection.setAutoCommit(false);

            Coupon coupon = couponDao.findByOrderId(order);
            if (coupon != null) {
                couponDao.setOrderNull(coupon.getId());
            }
            orderDao.delete(order);

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }

    }

    public List<Coupon> getFreeCoupons(User user) {
        try (Connection connection = connectionPool.getConnection()) {
            CouponDao couponDao = daoFactory.createCouponDao(connection);

            return couponDao.findByIdUserAndOrderNull(user.getId());
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }

    }


    public boolean confirmOrder(Long id, User user) {
        try (Connection connection = connectionPool.getConnection()) {
            OrderDao orderDao = daoFactory.createOrderDao(connection);
            UserDao userDao = daoFactory.createUserDao(connection);
            TaxiDao taxiDao = daoFactory.createTaxiDao(connection);
            CouponDao couponDao = daoFactory.createCouponDao(connection);
            connection.setAutoCommit(false);

            Order order = orderDao.findById(id);
            userDao.updateMoney(user.builder()
                    .moneySpent(user.getMoneySpent() + order.getCost())
                    .build());
            orderDao.updateStatus(order.getId(),
                    Status.Done);
            orderDao.updateTaxiStatus(order.getTaxi().getId());
            taxiDao.updateLocation(order.getTaxi().getId(),
                    order.getEndStreet(),
                    order.getEndHouse());
            couponDao.deleteByOrderId(order.getId());

            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }

    }

}
