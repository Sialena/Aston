package sorting;

public enum StrategyType {
    BUBBLE,
    QUICK,
    INSERTION,
    EVEN_ODD;

    public static StrategyType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Strategy name is null");
        }
        return StrategyType.valueOf(value.trim().toUpperCase());
    }
}

