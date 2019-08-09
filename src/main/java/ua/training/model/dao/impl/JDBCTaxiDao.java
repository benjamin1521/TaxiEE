package ua.training.model.dao.impl;

import ua.training.model.dao.TaxiDao;
import ua.training.model.dao.mapper.TaxiMapper;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCTaxiDao implements TaxiDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private TaxiMapper mapper = new TaxiMapper();

    public JDBCTaxiDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Taxi> findByTypeAndBusy(Type type) throws SQLException {
        String query = queries.getString("get.taxi.free");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, type.name());
            try (ResultSet rs = ps.executeQuery()) {
                return mapper.extractAll(rs);
            }
        }
    }

    @Override
    public void updateLocation(Long id, Street street, int house) throws SQLException {
        String query = queries.getString("update.taxi.location");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, street.name());
            ps.setInt(2, house);
            ps.setLong(3, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void create(Taxi taxi) throws SQLException {
        String query = queries.getString("create.taxi");
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,taxi.getType().name());
            ps.setString(2,taxi.getDriverName());
            ps.setString(3,taxi.getCarNumber());
            ps.setBoolean(4,taxi.getBusy());
            ps.setString(5,taxi.getLocationStreet().name());
            ps.setInt(6,taxi.getLocationHouse());
            ps.execute();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
