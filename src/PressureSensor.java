
abstract class PressureSensor extends Sensor {
    private final String unit = "Bar";
    protected double measurementValue;

    @Override
    double getValue() {
        return this.measurementValue;
    }

    @Override
    public String getUnit() {
        return this.unit;
    }
}
