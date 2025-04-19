package core.strategy;

public class BalancedStrategy implements RaceStrategy {
    @Override
    public void execute() {
        System.out.println("Strateji: Dengeli sürüş - Risk ve güvenlik dengede.");
    }
}
