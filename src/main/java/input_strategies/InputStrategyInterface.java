package input_strategies;

import entity.Bus;
import java.util.stream.Stream;

@FunctionalInterface
public interface InputStrategyInterface {
    Stream<Bus> write();
}
