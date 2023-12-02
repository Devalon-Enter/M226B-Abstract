import java.util.Random;

public class SpeedSensorImpl2 extends SpeedSensor{
    private final String name = "SpeedSensorImpl2";
    @Override
    String getName() {
        return this.name;
    }

    @Override
    double doMeasurement() {
        Random random = new Random();
        measurementValue = random.nextDouble() * 260.0;
        return measurementValue;
    }
}
