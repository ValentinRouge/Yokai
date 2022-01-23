package yokai.objects;

public class HintCard {
    private final NumberHintCard numberOfFamilies;

    private final Families family1;

    private final Families family2;

    private final Families family3;

    public NumberHintCard getNumberOfFamilies() {
        return numberOfFamilies;
    }

    public Families getFamily1() {
        return family1;
    }

    public Families getFamily2() {
        return family2;
    }

    public Families getFamily3() {
        return family3;
    }

    public HintCard(Families family1) {
        this.numberOfFamilies = NumberHintCard.oneColor;
        this.family1 = family1;
        this.family2 = null;
        this.family3 = null;
    }
    public HintCard(Families family1, Families family2) {
        this.numberOfFamilies = NumberHintCard.twoColor;
        this.family1 = family1;
        this.family2 = family2;
        this.family3 = null;
    }
    public HintCard(Families family1, Families family2, Families family3) {
        this.numberOfFamilies = NumberHintCard.threeColor;
        this.family1 = family1;
        this.family2 = family2;
        this.family3 = family3;
    }

}