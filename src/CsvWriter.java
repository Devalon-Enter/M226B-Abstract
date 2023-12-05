import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class CsvWriter implements Runnable{

    private String fileName;
    private Sensor sensor;
    private File file;

    public CsvWriter(String fileName, Sensor sensor) {
        this.fileName = fileName+".csv";
        this.sensor = sensor;

        createFile(fileName+".csv");
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


    public void writeToFile(String sensorName, String unit, Date timeStamp, double measuredValue) {
        System.out.println("Entered writeToFile");
        try {
            if (file.getName().equals(fileName)) {
                BufferedWriter outputStream = new BufferedWriter(new FileWriter(fileName, true));
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

}
