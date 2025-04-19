package observer;

public class RaceDisplay implements RaceObserver {
    @Override
    public void update(String eventMessage) {
        System.out.println("📺 Yarış ekranı güncellendi: " + eventMessage);
    }
}
