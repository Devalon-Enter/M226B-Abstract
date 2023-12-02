import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class CsvWriter implements Runnable{

    private static final Object lock = new Object();
    private String fileName;
    private Sensor sensor;
    private File file;
    private BufferedWriter outputStream;

    public CsvWriter(String fileName, Sensor sensor) {
        this.fileName = fileName+".csv";
        this.sensor = sensor;

        createFile(fileName+".csv");
        initializeWriter();
    }

    public void createFile(String fileName) {
        try {
            file = new File(fileName);
            if(file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    private void initializeWriter() {
        try {
            outputStream = new BufferedWriter(new FileWriter(fileName, true)); // Use append mode
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(String sensorName, String unit, Date timeStamp, double measuredValue) {
        System.out.println("Entered writeToFile");
        try {
            if (file.getName().equals(fileName)) {
                outputStream.write(sensorName + ",");
                outputStream.write(unit + ",");
                outputStream.write(timeStamp + ",");
                outputStream.write(String.valueOf(measuredValue));

                outputStream.newLine(); // Add a new line for the next entry

                outputStream.flush();
                outputStream.close(); // Close the stream

                System.out.println("Wrote to file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String sensorName;
        String unit;
        Date timeStamp;
        double measuredValue;

                sensorName = sensor.getName();
                unit = sensor.getUnit();
                timeStamp = new Date();
                measuredValue = sensor.doMeasurement();
                System.out.println("Thread running");

                writeToFile(sensorName, unit, timeStamp, measuredValue);
                System.out.println("After writeToFile");
    }

    // Add this method to close the writer when necessary
    public void closeWriter() {
        try {
            if (outputStream != null) {
                outputStream.close();
                System.out.println("Closed writer");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
