import java.util.Random;

public class Aqualung2021ProDivePressureSensorImpl extends PressureSensor {
    private final String name = "Aqualung2021ProDivePressureSensorImpl";

    @Override
    double doMeasurement() {
        Random random = new Random();
        measurementValue = random.nextDouble() * 10.0;
        return measurementValue;
    }

    @Override
    String getName() {
        return this.name;
    }
}
