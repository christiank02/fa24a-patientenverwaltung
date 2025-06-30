package patientenverwaltung.datalayer.dataaccessobjects.db.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import patientenverwaltung.datalayer.exceptions.DaoException;
import patientenverwaltung.models.Pflegekraft;

public class PflegekraftDaoSqlite extends AbstractDaoSqlite<Pflegekraft, Long> {

    public PflegekraftDaoSqlite(String url) throws DaoException {
        super(url);
    }

    @Override
    protected String getTableName() {
        return "pflegekraft";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "id";
    }

    @Override
    protected String getSqlCreateTableIfNotExists() {
        return "CREATE TABLE IF NOT EXISTS pflegekraft (" +
               "id INTEGER PRIMARY KEY AUTOINCREMENT," +
               "vorname TEXT NOT NULL," +
               "nachname TEXT NOT NULL," +
               "telefon TEXT" +
               ")";
    }

    @Override
    protected String getSqlInsert() {
        return "INSERT INTO pflegekraft (vorname, nachname, telefon) VALUES (?, ?, ?)";
    }

    @Override
    protected String getSqlUpdate() {
        return "UPDATE pflegekraft SET vorname = ?, nachname = ?, telefon = ? WHERE id = ?";
    }

    @Override
    protected String getSqlReadId() {
        return "SELECT * FROM pflegekraft WHERE id = ?";
    }

    @Override
    protected String getSqlReadAll() {
        return "SELECT * FROM pflegekraft";
    }

    @Override
    protected String getSqlDelete() {
        return "DELETE FROM pflegekraft WHERE id = ?";
    }

    @Override
    protected Pflegekraft mapResultSetToObject(ResultSet resultSet) throws SQLException {
        Pflegekraft pflegekraft = new Pflegekraft();
        pflegekraft.setId(resultSet.getLong("id"));
        pflegekraft.setVorname(resultSet.getString("vorname"));
        pflegekraft.setNachname(resultSet.getString("nachname"));
        pflegekraft.setTelefon(resultSet.getString("telefon"));
        return pflegekraft;
    }

    @Override
    protected void setInsertStatement(PreparedStatement preparedStatement, Pflegekraft objectToInsert) throws SQLException {
        preparedStatement.setString(1, objectToInsert.getVorname());
        preparedStatement.setString(2, objectToInsert.getNachname());
        preparedStatement.setString(3, objectToInsert.getTelefon());
    }

    @Override
    protected void setGeneratedIdToObject(PreparedStatement preparedStatement, Pflegekraft objectToInsert) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                objectToInsert.setId(generatedKeys.getLong(1));
            }
        }
    }

    @Override
    protected void setUpdateStatement(PreparedStatement preparedStatement, Pflegekraft objectToUpdate) throws SQLException {
        preparedStatement.setString(1, objectToUpdate.getVorname());
        preparedStatement.setString(2, objectToUpdate.getNachname());
        preparedStatement.setString(3, objectToUpdate.getTelefon());
        preparedStatement.setLong(4, objectToUpdate.getId());
    }
}