package patientenverwaltung.datalayer.dataaccessobjects.file.services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilePersistenceServiceXml<T> implements FilePersistenceService<T> {

    @Override
    public List<T> readFile(Class<T> classType, Path filePath) {
        List<T> items = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XmlWrapper<T> wrapper = (XmlWrapper<T>) unmarshaller.unmarshal(filePath.toFile());
            if(wrapper.getItems() != null) {
                items =  wrapper.getItems();
            }
        }catch(JAXBException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void writeFile(Class<T> classType, List<T> listToPersist, Path filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlWrapper.class, classType);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            XmlWrapper<T> wrapper = new XmlWrapper<>(listToPersist);
            marshaller.marshal(wrapper, filePath.toFile());
        }catch(JAXBException e) {
            e.printStackTrace();
        }
    }
}