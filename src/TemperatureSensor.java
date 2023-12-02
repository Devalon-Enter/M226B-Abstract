abstract class TemperatureSensor extends Sensor {
    private String unit = "Celsius";
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
