package cli;

import core.composite.Team;
import core.composite.TeamRepository;
import core.model.Driver;
import core.model.Car;
import factory.TeamFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Team> teams = new ArrayList<>();

    public void menu() {
        int choice;
        do {
            System.out.println("\n--- Takımlar Menüsü ---");
            System.out.println("1. Takım Oluştur");
            System.out.println("2. Takımları Listele");
            System.out.println("3. Takım Detaylarını Görüntüle");
            System.out.println("4. Geri Dön");
            System.out.print("Seçimin: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // dummy read

            switch (choice) {
                case 1 -> createTeam();
                case 2 -> listTeams();
                case 3 -> showTeamDetails();
                case 4 -> System.out.println("Ana menüye dönülüyor...");
                default -> System.out.println("Geçersiz seçim!");
            }
        } while (choice != 4);
    }

    private void createTeam() {
        System.out.print("Takım adı: ");
        String name = scanner.nextLine();
        Team team = TeamFactory.createTeam(name);
        teams.add(team);
        TeamRepository.addTeam(team);
        System.out.println("✅ Takım oluşturuldu ve kayıt edildi.");
    }

    private void listTeams() {
        if (teams.isEmpty()) {
            System.out.println("Henüz takım eklenmedi.");
        } else {
            System.out.println("\n📋 Takımlar:");
            for (int i = 0; i < teams.size(); i++) {
                System.out.println((i + 1) + ". " + teams.get(i).getName());
            }
        }
    }

    private void showTeamDetails() {
        if (teams.isEmpty()) {
            System.out.println("Takım bulunamadı.");
            return;
        }

        listTeams();
        System.out.print("Detaylarını görmek istediğiniz takım numarası: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= teams.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        Team selectedTeam = teams.get(index);
        selectedTeam.showTeamDetails();
    }
}
