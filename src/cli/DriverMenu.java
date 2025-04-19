package cli;

import core.model.Driver;
import core.strategy.*;
import core.composite.Team;
import core.composite.TeamRepository;
import factory.DriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Driver> drivers;

    public DriverMenu(List<Driver> sharedDriverList) {
        this.drivers = sharedDriverList;
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n--- Pilotlar Menüsü ---");
            System.out.println("1. Pilot Ekle");
            System.out.println("2. Pilotları Listele");
            System.out.println("3. Pilotu Takıma Ekle");
            System.out.println("4. Strateji Ata");
            System.out.println("5. Geri Dön");
            System.out.print("Seçimin: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createDriver();
                case 2 -> listDrivers();
                case 3 -> assignToTeam();
                case 4 -> assignStrategy();
                case 5 -> System.out.println("Ana menüye dönülüyor...");
                default -> System.out.println("Geçersiz seçim!");
            }
        } while (choice != 5);
    }

    private void createDriver() {
        System.out.print("Pilot adı: ");
        String name = scanner.nextLine();
        Driver driver = DriverFactory.createDriver(name);
        drivers.add(driver);
        System.out.println("✅ Pilot eklendi.");
    }

    private void listDrivers() {
        if (drivers.isEmpty()) {
            System.out.println("Henüz pilot eklenmedi.");
        } else {
            System.out.println("\n🧑‍✈️ Pilotlar:");
            for (int i = 0; i < drivers.size(); i++) {
                System.out.println((i + 1) + ". " + drivers.get(i));
            }
        }
    }

    private void assignToTeam() {
        if (drivers.isEmpty()) {
            System.out.println("Önce pilot oluşturmalısın.");
            return;
        }

        listDrivers();
        System.out.print("Takıma eklenecek pilotun numarası: ");
        int pilotIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (pilotIndex < 0 || pilotIndex >= drivers.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        List<Team> teams = TeamRepository.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Takım bulunamadı. Önce takım ekleyin.");
            return;
        }

        System.out.println("Takım Seç:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).getName());
        }

        System.out.print("Takım numarası: ");
        int teamIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (teamIndex < 0 || teamIndex >= teams.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        Team selectedTeam = teams.get(teamIndex);
        selectedTeam.addDriver(drivers.get(pilotIndex));
        System.out.println("✅ Pilot takıma eklendi.");
    }

    private void assignStrategy() {
        if (drivers.isEmpty()) {
            System.out.println("Önce pilot oluşturmalısın.");
            return;
        }

        listDrivers();
        System.out.print("Strateji atanacak pilotun numarası: ");
        int pilotIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (pilotIndex < 0 || pilotIndex >= drivers.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        Driver selectedDriver = drivers.get(pilotIndex);

        System.out.println("Strateji Seç:");
        System.out.println("1. Agresif");
        System.out.println("2. Savunmacı");
        System.out.println("3. Dengeli");
        System.out.print("Seçim: ");
        int strategyChoice = scanner.nextInt();
        scanner.nextLine();

        RaceStrategy strategy = switch (strategyChoice) {
            case 1 -> new AggressiveStrategy();
            case 2 -> new DefensiveStrategy();
            case 3 -> new BalancedStrategy();
            default -> null;
        };

        if (strategy != null) {
            selectedDriver.setStrategy(strategy);
            System.out.println("✅ Strateji atandı. Simülasyon testi:");
            selectedDriver.performStrategy();
        } else {
            System.out.println("Geçersiz strateji seçimi.");
        }
    }
}
