package core.model;

public class Car {
    private String model;
    private int horsepower;

    public Car(String model, int horsepower) {
        this.model = model;
        this.horsepower = horsepower;
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public String toString() {
        return model + " (" + horsepower + " HP)";
    }
}
