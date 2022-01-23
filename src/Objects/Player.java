package Objects;

public class Player {
    private final String name; // on declare un attribut

    public Player(String name) { // constructeur si je creer une personne je lui donne un nom
        this.name = name;
    }

    public String getName() { //  getter
        return name;
    }
}
