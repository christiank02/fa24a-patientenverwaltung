package patientenverwaltung.datalayer.dataaccessobjects.file.services;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilePersistenceServiceCsv<T> implements FilePersistenceService<T> {

    private char separator;

    public FilePersistenceServiceCsv(char separator) {
        this.separator = separator;
    }

    @Override
    public List<T> readFile(Class<T> classType, Path filePath) {
        try (Reader reader = new FileReader(filePath.toFile())) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(classType)
                    .withSeparator(separator)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeFile(Class<T> classType, List<T> listToPersist, Path filePath) {
        ColumnPositionMappingStrategy<T> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(classType);
        String[] columns = getCsvColumnNames(classType);
        mappingStrategy.setColumnMapping(columns);

        String header = String.join(String.valueOf(separator), columns) + "\n";

        try (Writer writer = new FileWriter(filePath.toFile())) {
            writer.write(header);
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(separator)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            beanToCsv.write(listToPersist);
        } catch (Exception e) {
            throw new RuntimeException("Error writing to CSV file: " + e.getMessage(), e);
        }
    }

    private String[] getCsvColumnNames(Class<T> classType) {
        List<String> columnNames = new ArrayList<>();
        for (Field field : classType.getDeclaredFields()) {
            if (field.isAnnotationPresent(CsvBindByName.class)) {
                CsvBindByName csvBindByName = field.getAnnotation(CsvBindByName.class);
                columnNames.add(csvBindByName.column());
            }
        }
        return columnNames.toArray(new String[0]);
    }
}
