package factory;

import core.model.Car;

public class CarFactory {
    public static Car createCar(String model, int horsepower) {
        Car car = new Car(model, horsepower);
        System.out.println("Yeni araç oluşturuldu: " + car);
        return car;
    }
}
