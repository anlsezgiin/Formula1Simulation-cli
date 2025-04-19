package core.model;

import core.strategy.RaceStrategy;

public class Driver {
    private String name;
    private int number;
    private RaceStrategy strategy;

    public Driver(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void setStrategy(RaceStrategy strategy) {
        this.strategy = strategy;
    }
    public RaceStrategy getStrategy() {
        return strategy;
    }


    public void performStrategy() {
        if (strategy != null) {
            strategy.execute();
        } else {
            System.out.println(name + " için strateji atanmadı.");
        }
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Pilot #" + number + " - " + name;
    }
}
