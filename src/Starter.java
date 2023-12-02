import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Starter {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Map<String, Sensor> sensors = new HashMap<>();
        String fileName;
        Sensor sensorToMonitor;
        int interval;

        sensors.put("OilTempBoschImpl", new OilTempBoschImpl());
        sensors.put("SpeedSensorImpl", new SpeedSensorImpl());
        sensors.put("Barometric1000PressureSensorImpl", new Barometric1000PressureSensorImpl());

        System.out.println("Bitte geben sie einen Namen für ihr File ein: ");
        fileName = in.nextLine();

        System.out.println("Geben sie an, welchen der folgenden Sensoren sie überwachen möchten: ");
        for(Sensor sensor: sensors.values()) {
            System.out.println(sensor.getName());
        }
        System.out.println(in.nextLine());
        String sensorToMonitorName = in.nextLine();

        System.out.println("Geben Sie an, in welchem Interval die Daten in die Datei geschrieben werden sollen: (In Millisekunden)");
        interval = Integer.parseInt(in.nextLine());

        sensorToMonitor = sensors.getOrDefault(sensorToMonitorName, null);


        CsvWriter csvWriter = new CsvWriter(fileName, sensorToMonitor);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(csvWriter, 0, interval, TimeUnit.MILLISECONDS);

        while(!executorService.isShutdown()) {
            if(in.nextLine().equals("Close")) {
                executorService.shutdown();
                csvWriter.closeWriter();
            }
        }
//        t1.setName("SensorThread");
//        t1.start();


    }
}