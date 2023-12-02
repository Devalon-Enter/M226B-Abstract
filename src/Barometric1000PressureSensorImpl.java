import java.util.Random;

public class Barometric1000PressureSensorImpl extends PressureSensor {
    private final String name = "Barometric1000PressureSensorImpl";

    @Override
    double doMeasurement() {
        Random random = new Random();
        measurementValue = 0.5 + (random.nextDouble() * 0.56);
        return measurementValue;
    }

    @Override
    String getName() {
        return this.name;
    }
}
