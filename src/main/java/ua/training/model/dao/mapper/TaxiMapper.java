package ua.training.model.dao.mapper;

import ua.training.model.entities.Taxi;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxiMapper implements ObjectMapper<Taxi> {
    @Override
    public Taxi extractOne(ResultSet rs) throws SQLException {
        return Taxi.newBuilder()
                .id(rs.getLong("id"))
                .busy(rs.getBoolean("busy"))
                .carNumber(rs.getString("car_number"))
                .driverName(rs.getString("driver_name"))
                .locationHouse(rs.getInt("location_house"))
                .locationStreet(Street.valueOf(rs.getString("location_street")))
                .type(Type.valueOf(rs.getString("type")))
                .build();
    }

    @Override
    public List<Taxi> extractAll(ResultSet rs) throws SQLException {
        List<Taxi> result = new ArrayList<>();
        while (rs.next()) {
            result.add(extractOne(rs));
        }
        return result;
    }
}
