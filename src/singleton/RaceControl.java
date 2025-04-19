package singleton;

import core.model.Driver;
import core.strategy.*;
import observer.RaceObserver;

import java.util.*;

public class RaceControl {

    private static RaceControl instance;

    private final List<Driver> drivers = new ArrayList<>();
    private final List<RaceObserver> observers = new ArrayList<>();

    private RaceControl() {
        System.out.println("🏁 Yarış Kontrol Merkezi Başlatıldı.");
    }

    public static RaceControl getInstance() {
        if (instance == null) {
            instance = new RaceControl();
        }
        return instance;
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }

    public void addObserver(RaceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RaceObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (RaceObserver o : observers) {
            o.update(message);
        }
    }

    public void simulateAccident(String driverName) {
        System.out.println("💥 Kaza bildirimi: " + driverName + " yarış dışı kaldı!");
        notifyObservers("Kaza: " + driverName + " yarış dışı kaldı!");
    }

    public void startRace() {
        if (drivers.isEmpty()) {
            System.out.println("Yarışa katılacak pilot yok.");
            return;
        }

        System.out.println("\n🏎️ Yarış başlıyor!\n");

        Random random = new Random();
        Map<Driver, Integer> results = new HashMap<>();

        for (Driver driver : drivers) {
            int base = random.nextInt(101); // 0–100
            int bonus = 0;

            switch (driverStrategyName(driver)) {
                case "Aggressive" -> bonus += 15;
                case "Balanced" -> bonus += 10;
                case "Defensive" -> bonus += 5;
            }

            int total = base + bonus;
            results.put(driver, total);

            System.out.printf("%s yarışıyor... (Strateji: %s) → Puan: %d\n",
                    driver.getName(),
                    driverStrategyName(driver),
                    total);
        }

        List<Map.Entry<Driver, Integer>> sorted = new ArrayList<>(results.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\n🏁 Yarış Sıralaması:");
        int place = 1;
        for (Map.Entry<Driver, Integer> entry : sorted) {
            System.out.printf("%d. %s → %d puan\n", place++, entry.getKey().getName(), entry.getValue());
        }

        System.out.println("\n🥇 Kazanan: " + sorted.get(0).getKey().getName() + " 🎉");

        drivers.clear(); // Yarıştan sonra temizle
    }

    private String driverStrategyName(Driver driver) {
        if (driver.getStrategy() instanceof AggressiveStrategy) return "Aggressive";
        if (driver.getStrategy() instanceof DefensiveStrategy) return "Defensive";
        if (driver.getStrategy() instanceof BalancedStrategy) return "Balanced";
        return "Bilinmiyor";
    }
}
