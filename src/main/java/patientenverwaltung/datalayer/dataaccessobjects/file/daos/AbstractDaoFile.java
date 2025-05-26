package patientenverwaltung.datalayer.dataaccessobjects.file.daos;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import patientenverwaltung.datalayer.dataaccessobjects.Dao;
import patientenverwaltung.datalayer.dataaccessobjects.file.services.FilePersistenceService;
import patientenverwaltung.datalayer.exceptions.DaoException;

public abstract class AbstractDaoFile<T, ID> implements Dao<T, ID> {

    protected final FilePersistenceService<T> filePersistenceService;
    protected final Class<T> objectType;
    protected final Path filePath;
    protected List<T> cachedObjectList;

    public AbstractDaoFile(FilePersistenceService<T> filePersistenceService, Class<T> objectType, Path filePath) {
        this.filePersistenceService = filePersistenceService;
        this.objectType = objectType;
        this.filePath = filePath;
        this.cachedObjectList = loadObjectList();
    }

    @Override
    public T create() {
        throw new UnsupportedOperationException("Error operation not supported. Use create(T objectToInsert) instead.");
    }

    @Override
    public T create(T objectToInsert) {
        setIdToObjectToInsert(objectToInsert);
        cachedObjectList.add(objectToInsert);
        saveObjectList(cachedObjectList);
        return objectToInsert;
    }

    @Override
    public T read(ID id) {
        return cachedObjectList.stream()
                .filter(obj -> hasMatchingId(obj, id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> read() {
        return new ArrayList<>(cachedObjectList);
    }

    @Override
    public void update(T objectToUpdate) throws DaoException {
        ID id = getIdFromObject(objectToUpdate);
        Optional<T> existing = cachedObjectList.stream()
                .filter(obj -> hasMatchingId(obj, id))
                .findFirst();
        if (existing.isPresent()) {
            cachedObjectList.remove(existing.get());
            cachedObjectList.add(objectToUpdate);
            saveObjectList(cachedObjectList);
        } else {
            throw new DaoException("Object to update not found. Id: " + id);
        }
    }

    @Override
    public void delete(ID id) throws DaoException {
        boolean removed = cachedObjectList.removeIf(obj -> hasMatchingId(obj, id));
        if (removed) {
            saveObjectList(cachedObjectList);
        } else {
            throw new DaoException("Object to delete not found. Id: " + id);
        }
    }

    protected abstract ID getIdFromObject(T object);

    protected abstract void setIdToObjectToInsert(T objectToInsert);

    private List<T> loadObjectList() {
        List<T> list = filePersistenceService.readFile(objectType, filePath);
        return list != null ? list : new ArrayList<>();
    }

    private void saveObjectList(List<T> objectList) {
        filePersistenceService.writeFile(objectType, objectList, filePath);
    }

    private boolean hasMatchingId(T object, ID id) {
        return getIdFromObject(object).equals(id);
    }

}