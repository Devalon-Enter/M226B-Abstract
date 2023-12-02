abstract class SpeedSensor extends Sensor {
    private String unit = "Kilometer per hour";
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
