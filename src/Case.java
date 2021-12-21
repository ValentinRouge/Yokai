public class Case {

    private HintCard hintCard;
    private YokaiCard yokaiCard;

    public HintCard getHintCard() {
        return hintCard;
    }

    public YokaiCard getYokaiCard() {
        return yokaiCard;
    }

    public void setHintCard(HintCard hintCard) {
        this.hintCard = hintCard;
    }

    public void setYokaiCard(YokaiCard yokaiCard) {
        this.yokaiCard = yokaiCard;
    }


    public Case() {
        hintCard = null;
        yokaiCard = null;
    }
}
