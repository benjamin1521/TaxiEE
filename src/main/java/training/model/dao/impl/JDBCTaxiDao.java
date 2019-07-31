package training.model.dao.impl;

import ua.training.model.dao.TaxiDao;
import ua.training.model.dao.mapper.TaxiMapper;

import java.sql.Connection;
import java.util.ResourceBundle;

public class JDBCTaxiDao implements TaxiDao {
    private Connection connection;
    private ResourceBundle queries = ResourceBundle.getBundle("sql-queries");
    private TaxiMapper mapper = new TaxiMapper();

    public JDBCTaxiDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {

    }
}
