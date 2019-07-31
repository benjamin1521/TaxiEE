package training.model.dao.mapper;

import ua.training.model.entities.Taxi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaxiMapper implements ObjectMapper<Taxi>{
    @Override
    public Taxi extractOne(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public List<Taxi> extractAll(ResultSet rs) throws SQLException {
        return null;
    }
}
