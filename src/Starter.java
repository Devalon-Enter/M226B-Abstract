import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Starter {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        Map<Integer, Sensor> sensors = new HashMap<>();
        String fileName;
        int sensorToMonitorName;
        Sensor sensorToMonitor;
        int interval;

        sensors.put(1, new OilTempBoschImpl());
        sensors.put(2, new SpeedSensorImpl());
        sensors.put(3, new Barometric1000PressureSensorImpl());

        // Filename entry
        System.out.println("Bitte geben sie einen Namen für ihr File ein: ");
        fileName = in.nextLine();


        // Sensor entry
        System.out.println("Geben sie an, welchen der folgenden Sensoren sie überwachen möchten: ");
        sensors.forEach((key, value) -> System.out.println("Press " + key + " to choose: " + value.getName()));
        System.out.println("\n");

        String sensorKey = in.nextLine();

        // Check if number was given as input
        if(!sensorKey.matches("[a-zA-Z]")) {
            sensorToMonitorName = Integer.parseInt(sensorKey);
        } else {
            throw new Exception("Please enter a valid number!");
        }

        // Check if number was within valid range
        if(sensorToMonitorName > sensors.size()) {
            throw new Exception("Please enter a valid number from 1 to " + sensors.size());
        }


        // Interval entry
        System.out.println("Geben Sie an, in welchem Interval die Daten in die Datei geschrieben werden sollen: (In Millisekunden)");
        String intervalEntry = in.nextLine();
        if(!intervalEntry.matches("[a-zA-Z]")) {
            interval = Integer.parseInt(intervalEntry);
        } else {
            throw new Exception("Please enter valid number!");
        }

        // Get the Sensor to monitor from the map
        sensorToMonitor = sensors.getOrDefault(sensorToMonitorName, null);
        if(sensorToMonitor == null) {
            throw new Exception("There was no Sensor found that matches your choice");
        }


        // Create a new csvWriter instance
        CsvWriter csvWriter = new CsvWriter(fileName, sensorToMonitor);


        // Create a new Thread
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // Start the Thread created beforehand
        executorService.scheduleAtFixedRate(csvWriter, 0, interval, TimeUnit.MILLISECONDS);

        // Check if the Thread is not shutdown
        // If still running check for inputs "e". If detected shut the Thread and the App down.
        while(!executorService.isShutdown()) {
            if(in.nextLine().equals("e")) {
                executorService.shutdown();
            }
        }
    }
}