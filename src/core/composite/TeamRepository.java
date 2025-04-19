package core.composite;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {
    private static final List<Team> teams = new ArrayList<>();

    public static void addTeam(Team team) {
        teams.add(team);
    }

    public static List<Team> getAllTeams() {
        return teams;
    }
}
