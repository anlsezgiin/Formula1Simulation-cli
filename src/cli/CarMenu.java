package cli;

import core.model.Car;
import factory.CarFactory;
import core.composite.Team;
import core.composite.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Car> cars = new ArrayList<>();

    public void menu() {
        int choice;
        do {
            System.out.println("\n--- Araçlar Menüsü ---");
            System.out.println("1. Araç Ekle");
            System.out.println("2. Araçları Listele");
            System.out.println("3. Aracı Takıma Ekle");
            System.out.println("4. Geri Dön");
            System.out.print("Seçimin: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createCar();
                case 2 -> listCars();
                case 3 -> assignCarToTeam();
                case 4 -> System.out.println("Ana menüye dönülüyor...");
                default -> System.out.println("Geçersiz seçim!");
            }
        } while (choice != 4);
    }

    private void createCar() {
        System.out.print("Araç modeli: ");
        String model = scanner.nextLine();
        System.out.print("Beygir gücü: ");
        int hp = scanner.nextInt();
        scanner.nextLine();

        Car car = CarFactory.createCar(model, hp);
        cars.add(car);
        System.out.println("✅ Araç eklendi.");
    }

    private void listCars() {
        if (cars.isEmpty()) {
            System.out.println("Henüz araç eklenmedi.");
        } else {
            System.out.println("\n🚗 Araçlar:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println((i + 1) + ". " + cars.get(i));
            }
        }
    }

    private void assignCarToTeam() {
        if (cars.isEmpty()) {
            System.out.println("Önce araç eklemelisin.");
            return;
        }

        listCars();
        System.out.print("Takıma eklenecek aracın numarası: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (carIndex < 0 || carIndex >= cars.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        List<Team> teams = TeamRepository.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Takım bulunamadı. Önce takım ekle.");
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
        selectedTeam.addCar(cars.get(carIndex));
        System.out.println("✅ Araç takıma eklendi.");
    }
}
