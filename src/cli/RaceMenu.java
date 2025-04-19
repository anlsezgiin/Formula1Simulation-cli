package cli;

import core.model.Driver;
import singleton.RaceControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RaceMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Driver> availableDrivers;

    public RaceMenu(List<Driver> allDrivers) {
        this.availableDrivers = allDrivers;
    }

    public void menu() {
        RaceControl raceControl = RaceControl.getInstance();

        if (availableDrivers.isEmpty()) {
            System.out.println("Yarışa katılacak pilot bulunamadı. Önce pilot ekleyin.");
            return;
        }

        System.out.println("\n🏁 Yarışa katılacak pilotları seçin (boş bırakmak için 0):");

        List<Driver> selected = new ArrayList<>();
        for (int i = 0; i < availableDrivers.size(); i++) {
            Driver driver = availableDrivers.get(i);
            System.out.println((i + 1) + ". " + driver);
        }

        System.out.println("Seçmek istediğiniz pilotların numaralarını aralarında virgül ile girin:");
        String input = scanner.nextLine(); // örnek: 1,3,4

        String[] choices = input.split(",");
        for (String choice : choices) {
            try {
                int index = Integer.parseInt(choice.trim()) - 1;
                if (index >= 0 && index < availableDrivers.size()) {
                    Driver selectedDriver = availableDrivers.get(index);
                    raceControl.registerDriver(selectedDriver);
                }
            } catch (NumberFormatException ignored) {}
        }

        raceControl.startRace();
    }
}
