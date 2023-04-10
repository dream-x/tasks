package springboot.CRUD.CsvUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;

import springboot.CRUD.Entity.Employee;

public class CSVFileCreator {
	
	private static Logger logger = Logger.getLogger("basicLogger");
	private static final String[] HEADER = {"id", "name", "surname"};
	
	public static File create(List<Employee> data, String fileName) throws IOException {
		File file = new File(fileName);
		try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeNext(HEADER);
            
            for(Employee employee : data) {
            	String[] line = {employee.getId().toString(), employee.getFirstName(), employee.getLastName()};
            	writer.writeNext(line);
            }
            writer.close();
            logger.info("CSV file successfully created");
            return new File(fileName);
        } catch (IOException e) {
        	logger.info("Error writing CSV file: " + e.getMessage());
            throw e;
        }
    }
}
