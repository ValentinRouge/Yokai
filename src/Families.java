import java.util.HashMap;
import java.util.Map;

public enum Families {
    Kitsune(0),
    Kappa(1),
    Rokurokubi(2),
    Oni(3);

    private int value;
    private static Map map = new HashMap<>();

    private Families(int value) {
        this.value = value;
    }

    static {
        for (Families pageType : Families.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Families valueOf(int pageType) {
        return (Families) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
