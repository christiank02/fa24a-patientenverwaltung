package patientenverwaltung.datalayer.dataaccessobjects.file.services;

import java.util.List;

public class XmlWrapper<T> {

    private List<T> items;

    public XmlWrapper() {}

    public XmlWrapper(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
