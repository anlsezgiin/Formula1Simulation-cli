package factory;

import core.composite.Team;

public class TeamFactory {
    public static Team createTeam(String name) {
        Team team = new Team(name);
        System.out.println("Yeni takım oluşturuldu: " + name);
        return team;
    }
}
