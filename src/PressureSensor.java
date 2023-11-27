abstract class PressureSensor extends Sensor {
    private String unit;
    protected double measurementValue;

    @Override
    double getValue() {
        return measurementValue;
    }

    @Override
    public String getUnit() {
        return unit;
    }
}
