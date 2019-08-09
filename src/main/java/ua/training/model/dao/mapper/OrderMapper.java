package ua.training.model.dao.mapper;

import ua.training.model.entities.Order;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractOne(ResultSet rs) throws SQLException {
        return Order.newBuilder()
                .id(rs.getLong("orders.id"))
                .cost(rs.getLong("cost"))
                .distance(rs.getInt("distance"))
                .drivingTime(rs.getDouble("driving_time"))
                .waitingTime(rs.getDouble("waiting_time"))
                .endHouse(rs.getInt("end_house"))
                .endStreet(Street.valueOf(rs.getString("end_street")))
                .startHouse(rs.getInt("start_house"))
                .startStreet(Street.valueOf(rs.getString("start_street")))
                .status(Status.valueOf(rs.getString("status")))
                .type(Type.valueOf(rs.getString("type")))
                .orderDate(rs.getTimestamp("order_date").toLocalDateTime())
                .Taxi(Taxi.newBuilder()
                        .id(rs.getLong("id_taxi"))
                        .carNumber(rs.getString("car_number"))
                        .build())
                .User(User.newBuilder()
                        .id(rs.getLong("id_user"))
                        .build())
                .build();
    }

    @Override
    public List<Order> extractAll(ResultSet rs) throws SQLException {
        List<Order> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extractOne(rs));
        }
        return result;
    }
}
