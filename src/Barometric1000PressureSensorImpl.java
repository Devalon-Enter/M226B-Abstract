abstract class Barometric1000PressureSensorImpl extends PressureSensor{
    @Override
    void doMeasurement() {
        int max = 10;
        int min = 1;
        int range = max - min + 1;
        measurementValue = (int)(Math.random() * range) + min;
    }
}
