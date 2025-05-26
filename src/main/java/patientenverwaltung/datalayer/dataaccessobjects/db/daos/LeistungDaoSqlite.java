package patientenverwaltung.datalayer.dataaccessobjects.db.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.models.Leistung;

public class LeistungDaoSqlite extends AbstractDaoSqlite<Leistung, String> {

    public LeistungDaoSqlite(String url) throws DaoException {
        super(url);
    }

    @Override
    protected String getTableName() {
        return "";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "";
    }

    @Override
    protected String getSqlCreateTableIfNotExists() {
        return "";
    }

    @Override
    protected String getSqlInsert() {
        return "";
    }

    @Override
    protected String getSqlUpdate() {
        return "";
    }

    @Override
    protected String getSqlReadId() {
        return "";
    }

    @Override
    protected String getSqlReadAll() {
        return "";
    }

    @Override
    protected String getSqlDelete() {
        return "";
    }

    @Override
    protected Leistung mapResultSetToObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void setInsertStatement(PreparedStatement preparedStatement, Leistung objectToInsert) throws SQLException {

    }

    @Override
    protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Leistung objectToInsert) throws SQLException {

    }

    @Override
    protected void setUpdateStatement(PreparedStatement preparedStatement, Leistung objectToUpdate) throws SQLException {

    }
}
