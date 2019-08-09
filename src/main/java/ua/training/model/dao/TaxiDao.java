package ua.training.model.dao;

import ua.training.model.entities.Taxi;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;

import java.sql.SQLException;
import java.util.List;

public interface TaxiDao extends AutoCloseable {
    List<Taxi> findByTypeAndBusy(Type type) throws SQLException;

    void updateLocation(Long id, Street street, int house) throws SQLException;

    void create(Taxi taxi) throws SQLException;
}
