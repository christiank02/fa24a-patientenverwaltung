package patientenverwaltung.datalayer.dataaccessobjects.db.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.models.Patient;

public class PatientDaoSqlite extends AbstractDaoSqlite<Patient, Long> {

    public PatientDaoSqlite(String url) throws DaoException {
        super(url);
    }

    @Override
    protected String getTableName() {
        return "patient";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "id";
    }

    @Override
    protected String getSqlCreateTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS patient (" +
               "id INTEGER PRIMARY KEY AUTOINCREMENT," +
               "vorname TEXT NOT NULL," +
               "nachname TEXT NOT NULL," +
               "geburtsdatum TEXT NOT NULL," +
               "pflegegrad INTEGER NOT NULL," +
               "zimmer TEXT," +
               "vermoegen REAL" +
               ")";
    }

    @Override
    protected String getSqlInsert() {
        return "INSERT INTO patient (vorname, nachname, geburtsdatum, pflegegrad, zimmer, vermoegen) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getSqlUpdate() {
        return "UPDATE patient SET vorname = ?, nachname = ?, geburtsdatum = ?, pflegegrad = ?, zimmer = ?, vermoegen = ? WHERE id = ?";
    }

    @Override
    protected String getSqlReadId() {
        return "SELECT * FROM patient WHERE id = ?";
    }

    @Override
    protected String getSqlReadAll() {
        return "SELECT * FROM patient";
    }

    @Override
    protected String getSqlDelete() {
        return "DELETE FROM patient WHERE id = ?";
    }

    @Override
    protected Patient mapResultSetToObject(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong("id"));
        patient.setVorname(resultSet.getString("vorname"));
        patient.setNachname(resultSet.getString("nachname"));
        patient.setGeburtsdatum(LocalDate.parse(resultSet.getString("geburtsdatum")));
        patient.setPflegegrad(resultSet.getInt("pflegegrad"));
        patient.setZimmer(resultSet.getString("zimmer"));
        patient.setVermoegen(resultSet.getDouble("vermoegen"));
        return patient;
    }

    @Override
    protected void setInsertStatement(PreparedStatement preparedStatement, Patient objectToInsert) throws SQLException {
        preparedStatement.setString(1, objectToInsert.getVorname());
        preparedStatement.setString(2, objectToInsert.getNachname());
        preparedStatement.setString(3, objectToInsert.getGeburtsdatum().toString());
        preparedStatement.setInt(4, objectToInsert.getPflegegrad());
        preparedStatement.setString(5, objectToInsert.getZimmer());
        preparedStatement.setDouble(6, objectToInsert.getVermoegen());
    }

    @Override
    protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Patient objectToInsert) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                objectToInsert.setId(generatedKeys.getLong(1));
            }
        }
    }

    @Override
    protected void setUpdateStatement(PreparedStatement preparedStatement, Patient objectToUpdate) throws SQLException {
        preparedStatement.setString(1, objectToUpdate.getVorname());
        preparedStatement.setString(2, objectToUpdate.getNachname());
        preparedStatement.setString(3, objectToUpdate.getGeburtsdatum().toString());
        preparedStatement.setInt(4, objectToUpdate.getPflegegrad());
        preparedStatement.setString(5, objectToUpdate.getZimmer());
        preparedStatement.setDouble(6, objectToUpdate.getVermoegen());
        preparedStatement.setLong(7, objectToUpdate.getId());
    }
}