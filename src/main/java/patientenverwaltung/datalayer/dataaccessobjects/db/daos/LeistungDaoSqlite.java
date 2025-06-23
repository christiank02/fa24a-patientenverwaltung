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
        return "leistung";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "lkNr";
    }

    @Override
    protected String getSqlCreateTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS leistung (" +
               "lkNr TEXT PRIMARY KEY," +
               "bezeichnung TEXT NOT NULL," +
               "beschreibung TEXT" +
               ")";
    }

    @Override
    protected String getSqlInsert() {
        return "INSERT INTO leistung (lkNr, bezeichnung, beschreibung) VALUES (?, ?, ?)";
    }

    @Override
    protected String getSqlUpdate() {
        return "UPDATE leistung SET bezeichnung = ?, beschreibung = ? WHERE lkNr = ?";
    }

    @Override
    protected String getSqlReadId() {
        return "SELECT * FROM leistung WHERE lkNr = ?";
    }

    @Override
    protected String getSqlReadAll() {
        return "SELECT * FROM leistung";
    }

    @Override
    protected String getSqlDelete() {
        return "DELETE FROM leistung WHERE lkNr = ?";
    }

    @Override
    protected Leistung mapResultSetToObject(ResultSet resultSet) throws SQLException {
        Leistung leistung = new Leistung();
        leistung.setLkNr(resultSet.getString("lkNr"));
        leistung.setBezeichnung(resultSet.getString("bezeichnung"));
        leistung.setBeschreibung(resultSet.getString("beschreibung"));
        return leistung;
    }

    @Override
    protected void setInsertStatement(PreparedStatement preparedStatement, Leistung objectToInsert) throws SQLException {
        preparedStatement.setString(1, objectToInsert.getLkNr());
        preparedStatement.setString(2, objectToInsert.getBezeichnung());
        preparedStatement.setString(3, objectToInsert.getBeschreibung());
    }

    @Override
    protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Leistung objectToInsert) throws SQLException {
    }

    @Override
    protected void setUpdateStatement(PreparedStatement preparedStatement, Leistung objectToUpdate) throws SQLException {
        preparedStatement.setString(1, objectToUpdate.getBezeichnung());
        preparedStatement.setString(2, objectToUpdate.getBeschreibung());
        preparedStatement.setString(3, objectToUpdate.getLkNr());
    }
}