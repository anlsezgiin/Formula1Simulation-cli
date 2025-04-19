package cli;

import core.model.Driver;
import singleton.RaceControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private final Scanner scanner = new Scanner(System.in);

    // Global listeler
    private final List<Driver> allDrivers = new ArrayList<>();

    // Menü bileşenleri
    private final TeamMenu teamMenu = new TeamMenu();
    private final DriverMenu driverMenu = new DriverMenu(allDrivers);
    private final CarMenu carMenu = new CarMenu();
    private final RaceMenu raceMenu = new RaceMenu(allDrivers);

    public void start() {
        int choice;
        do {
            System.out.println("\n🏁 Formula 1 CLI Simülasyonu");
            System.out.println("1. Takımlar");
            System.out.println("2. Pilotlar");
            System.out.println("3. Otomobiller");
            System.out.println("4. Yarış Başlat");
            System.out.println("5. Çıkış");
            System.out.print("Seçimin: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // dummy read

            switch (choice) {
                case 1 -> teamMenu.menu();
                case 2 -> driverMenu.menu();
                case 3 -> carMenu.menu();
                case 4 -> raceMenu.menu();
                case 5 -> System.out.println("Çıkılıyor...");
                default -> System.out.println("Geçersiz seçim!");
            }

        } while (choice != 5);
    }
}
