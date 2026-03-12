package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

public class StrategySelector {
    private InputStrategyInterface strategy;
    private Stream<Bus> busStream;

    public void setStrategy(InputStrategyInterface strategy) {
        this.strategy = strategy;
    }

    public Stream<Bus> getBuses() {
        busStream = strategy.write();
        return busStream;
    }
}
