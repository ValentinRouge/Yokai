package Objects;

import java.util.HashMap;
import java.util.Map;

public enum NumberHintCard {
    oneColor(1),
    twoColor(2),
    threeColor(3);

    private int value;
    private static Map map = new HashMap<>();

    private NumberHintCard(int value) {
        this.value = value;
    }

    static {
        for (NumberHintCard pageType : NumberHintCard.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static NumberHintCard valueOf(int pageType) {
        return (NumberHintCard) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
