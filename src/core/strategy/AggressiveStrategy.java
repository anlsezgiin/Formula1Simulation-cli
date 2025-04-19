package core.strategy;

public class AggressiveStrategy implements RaceStrategy {
    @Override
    public void execute() {
        System.out.println("Strateji: Agresif sürüş - Rakipleri hızlı geçmeye çalışıyor!");
    }
}
