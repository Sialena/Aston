package input_strategies;

import entity.Bus;
import java.util.List;

@FunctionalInterface
public interface InputStrategyInterface {
    List<Bus> write();
}
