import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;


public class Scene extends JPanel {


    //VARIABLES
    private ImageIcon icoBandeFond;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgBandeFond;

    private ImageIcon icoYookai1;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYookai1;

    private ImageIcon icoYookai2;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYookai2;

    private ImageIcon icoYookai3;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYookai3;

    private ImageIcon icoYookai4;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYookai4;

    private final int LARGEUR_YOOKAI = 1347;  //final car constt
    private final int HAUTEUR_YOOKAI = 707;  //final car constt

    //CONSTRUCTEUR
    //creation obj de type scene avec constructeur
    public Scene() {

        super(); // appel aux constructeur de la super classe
        //instancier ico et img
        this.icoBandeFond = new ImageIcon(getClass().getResource("/Image/redBackground.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgBandeFond = this.icoBandeFond.getImage(); // on associe ico a imagebandefond

        this.icoYookai1 = new ImageIcon(getClass().getResource("/Image/imgViolet.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYookai1 = this.icoYookai1.getImage(); // on associe ico a imagebandefond

        this.icoYookai2 = new ImageIcon(getClass().getResource("/Image/imgRouge.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYookai2 = this.icoYookai2.getImage(); // on associe ico a imagebandefond

        this.icoYookai3 = new ImageIcon(getClass().getResource("/Image/imgVert.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYookai3 = this.icoYookai3.getImage(); // on associe ico a imagebandefond

        this.icoYookai4 = new ImageIcon(getClass().getResource("/Image/imgBleu.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYookai4 = this.icoYookai4.getImage(); // on associe ico a imagebandefond
    }

    //METHODES
    public void paintComponent(Graphics g){
        g.drawImage(this.imgBandeFond, 0, 0, null);
        g.drawImage(this.imgYookai1, this.LARGEUR_YOOKAI, 0, null);
        g.drawImage(this.imgYookai2, 0, 0, null);
        g.drawImage(this.imgYookai3, this.LARGEUR_YOOKAI, this.HAUTEUR_YOOKAI, null);
        g.drawImage(this.imgYookai4, 0, this.HAUTEUR_YOOKAI, null);
    }
}
