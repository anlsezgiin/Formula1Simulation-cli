package factory;

import core.model.Driver;

public class DriverFactory {

    private static int driverCount = 1;

    public static Driver createDriver(String name) {
        Driver driver = new Driver(name, driverCount++);
        System.out.println("Yeni pilot oluşturuldu: " + driver);
        return driver;
    }
}
