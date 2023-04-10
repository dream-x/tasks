package springboot.CRUD.CsvUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import springboot.CRUD.Entity.Employee;

public class CSVFileCreator {
	
	private static Logger logger = Logger.getLogger("basicLogger");
	
	public static File create(List<Employee> data, String fileName) throws IOException {
		try {
            FileWriter csvWriter = new FileWriter(fileName);
            csvWriter.append("id,firstName,lastName\n");

            for (Employee employee : data) {
                csvWriter.append(String.valueOf(employee.getId())).append(",");
                csvWriter.append(employee.getFirstName()).append(",");
                csvWriter.append(employee.getLastName()).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
            logger.info("CSV file successfully created");
            return new File(fileName);
        } catch (IOException e) {
        	logger.info("Error writing CSV file: " + e.getMessage());
            throw e;
        }
    }
}
