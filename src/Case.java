public class Case {

    private HintCard hintCard;

    public Case(HintCard hintCard) {
        this.hintCard = hintCard;
    }

    public HintCard getHintCard() {
        return hintCard;
    }

    public static void test3() {
        Case hc1 = new Case(new HintCard(Families.Kitsune));
        System.out.println(hc1.getHintCard());
    }
    public static void test4() {
        Case hc2 = new Case(new HintCard(Families.Kitsune, Families.Rokurokubi, Families.Kappa));
        System.out.println(hc2.getHintCard().getFamily1());
    }

}
