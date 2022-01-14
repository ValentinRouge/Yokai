public class Case {

    private HintCard hintCard;
    private YokaiCard yokaiCard;
    private final Integer line;
    private final Integer col;

    public HintCard getHintCard() {
        return hintCard;
    }

    public YokaiCard getYokaiCard() {
        return yokaiCard;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getCol() {
        return col;
    }

    public void setHintCard(HintCard hintCard) {
        this.hintCard = hintCard;
    }

    public void setYokaiCard(YokaiCard yokaiCard) {
        this.yokaiCard = yokaiCard;
    }


    public Case(Integer line1, Integer col1) {
        this.line = line1;
        this.col = col1;
        hintCard = null;
        yokaiCard = null;

    }
}
