import java.util.Random;

public class Thermo2000Impl extends TemperatureSensor{
    private final String name = "Thermo2000Impl";

    @Override
    String getName() {
        return this.name;
    }

    @Override
    double doMeasurement() {
        Random random = new Random();
        measurementValue = random.nextDouble() * 300.0;
        return measurementValue;
    }
}
