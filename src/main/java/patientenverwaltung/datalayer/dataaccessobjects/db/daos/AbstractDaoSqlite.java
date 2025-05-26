package patientenverwaltung.datalayer.dataaccessobjects.db.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import patientenverwaltung.datalayer.dataaccessobjects.Dao;
import patientenverwaltung.datalayer.dataaccessobjects.db.services.ConnectionManager;
import patientenverwaltung.datalayer.dataaccessobjects.db.services.ConnectionManagerSqlite;
import patientenverwaltung.datalayer.exceptions.DaoException;

public abstract class AbstractDaoSqlite<T, ID> implements Dao<T, ID> {

    protected final ConnectionManager connectionManager;

    public AbstractDaoSqlite(String url) throws DaoException {
        this.connectionManager = new ConnectionManagerSqlite(url);
        createTableIfNotExists();
    }

    @Override
    public T create() {
        throw new UnsupportedOperationException("Error operation not supported. Use create(T objectToInsert) instead.");
    }

    @Override
    public T create(T objectToInsert) throws DaoException {
        createTableIfNotExists();
        try (Connection con = connectionManager.getNewConnection();
                PreparedStatement stmt = con.prepareStatement(getSqlInsert(), Statement.RETURN_GENERATED_KEYS)) {

            setInsertStatement(stmt, objectToInsert);
            stmt.executeUpdate();

            setGeneratedIdToObject(stmt, objectToInsert);

            return objectToInsert;
        } catch (SQLException e) {
            throw new DaoException("Error while inserting: " + e.getMessage());
        }
    }

    @Override
    public T read(ID id) throws DaoException {
        try (Connection con = connectionManager.getNewConnection();
                PreparedStatement stmt = con.prepareStatement(getSqlReadId())) {

            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToObject(rs);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<T> read() throws DaoException {
        List<T> result = new ArrayList<>();
        try (Connection con = connectionManager.getNewConnection();
                PreparedStatement stmt = con.prepareStatement(getSqlReadAll());
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                result.add(mapResultSetToObject(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while reading all entries: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void update(T objectToUpdate) throws DaoException {
        try (Connection con = connectionManager.getNewConnection();
                PreparedStatement stmt = con.prepareStatement(getSqlUpdate())) {

            setUpdateStatement(stmt, objectToUpdate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while updating: " + e.getMessage());
        }
    }

    @Override
    public void delete(ID id) throws DaoException {
        try (Connection con = connectionManager.getNewConnection();
                PreparedStatement stmt = con.prepareStatement(getSqlDelete())) {

            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while deleting: " + e.getMessage());
        }
    }

    protected void createTableIfNotExists() throws DaoException {
        try (Connection con = connectionManager.getNewConnection();
                Statement stmt = con.createStatement()) {
            stmt.execute(getSqlCreateTableIfNotExists());
        } catch (SQLException e) {
            throw new DaoException("Error while creating tables: " + e.getMessage());
        }
    }

    protected abstract String getTableName();

    protected abstract String getPrimaryKeyColumn();

    protected abstract String getSqlCreateTableIfNotExists();

    protected abstract String getSqlInsert();

    protected abstract String getSqlUpdate();

    protected abstract String getSqlReadId();

    protected abstract String getSqlReadAll();

    protected abstract String getSqlDelete();

    protected abstract T mapResultSetToObject(ResultSet resultSet) throws SQLException;

    protected abstract void setInsertStatement(PreparedStatement preparedStatement, T objectToInsert) throws SQLException;

    protected abstract void setGeneratedIdToObject(PreparedStatement preparedStatement, T objectToInsert) throws SQLException;

    protected abstract void setUpdateStatement(PreparedStatement preparedStatement, T objectToUpdate) throws SQLException;
}