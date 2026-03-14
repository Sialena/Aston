package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

public class StrategySelector {
    private InputStrategyInterface strategy;

    public void setStrategy(InputStrategyInterface strategy) {
        this.strategy = strategy;
    }

    public Stream<Bus> getBuses() {
        return strategy.busWrite();
    }
}
