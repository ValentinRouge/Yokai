public class Game {
    public static void test(){
        YokaiCard carte1 = new YokaiCard(Families.Rokurokubi);
        System.out.println(carte1.getFamily());
    }
    public static void test2(){
        Player name1 = new Player("Valentin");
        System.out.println(name1.getName());
    }
}
