package observer;

public class TeamManager implements RaceObserver {

    private String teamName;

    public TeamManager(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public void update(String eventMessage) {
        System.out.println("📢 [" + teamName + " Team Manager] Bildirim alındı: " + eventMessage);
    }
}
