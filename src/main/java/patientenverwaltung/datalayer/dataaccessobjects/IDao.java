package patientenverwaltung.datalayer.dataaccessobjects;

import java.util.List;

import patientenverwaltung.datalayer.exceptions.DaoException;

public interface IDao<T, ID> {

    T create();
    T create(T objectToInsert) throws DaoException;
    T read(ID id) throws DaoException;
    List<T> read() throws DaoException;
    void update(T objectToUpdate) throws DaoException;
    void delete(ID id) throws DaoException;
}
