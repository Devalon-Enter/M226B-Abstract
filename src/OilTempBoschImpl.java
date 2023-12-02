import java.util.Random;

public class OilTempBoschImpl extends TemperatureSensor{
    private final String name = "OilTempBoschImpl";

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
