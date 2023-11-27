abstract class Sensor {
    abstract String getUnit();
    abstract double getValue();
    abstract String getName();
    abstract void doMeasurement();
}
