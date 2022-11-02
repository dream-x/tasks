package application.utils;

import application.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CsvHelper {
    private static final String[] HEADERS = {"id", "name", "surname"};

    public static File toCsv(List<Person> data, String fileName) throws IOException {
        try (FileWriter out = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                     .withHeader(HEADERS))) {
            for (Person person : data) {
                printer.printRecord(person.getId(), person.getName(), person.getSurname());
            }
        } catch (IOException e) {
            log.error("Error while creating target CSV file");
            e.printStackTrace();
            throw e;
        }
        return new File(fileName);
    }
}
