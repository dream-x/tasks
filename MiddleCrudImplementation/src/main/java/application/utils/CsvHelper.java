package application.utils;

import application.entities.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvHelper {
    private static final String[] HEADERS = {"id", "name", "surname"};

    public static File toCsv(List<Person> data, String fileName) throws IOException {
        try (FileWriter out = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                     .withHeader(HEADERS))) {
            data.forEach(row -> {
                try {
                    printer.printRecord(row.getId(), row.getName(), row.getSurname());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return new File(fileName);
    }
}
