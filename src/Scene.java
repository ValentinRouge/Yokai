import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;


public class Scene extends JPanel {


    //VARIABLES
    private ImageIcon icoBandeFond;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgBandeFond;

    private ImageIcon icoYokai1;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYokai1;

    private ImageIcon icoYokai2;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYokai2;

    private ImageIcon icoYokai3;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYokai3;

    private ImageIcon icoYokai4;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgYokai4;

    private final int LARGEUR_YOKAI = 1347;  //final car constt
    private final int HAUTEUR_YOKAI = 707;  //final car constt

    //CONSTRUCTEUR
    //creation obj de type scene avec constructeur
    public Scene() {

        super(); // appel aux constructeur de la super classe
        //instancier ico et img
        this.icoBandeFond = new ImageIcon(getClass().getResource("/Image/redBackground.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgBandeFond = this.icoBandeFond.getImage(); // on associe ico a imagebandefond

        this.icoYokai1 = new ImageIcon(getClass().getResource("/Image/imgViolet.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYokai1 = this.icoYokai1.getImage(); // on associe ico a imagebandefond

        this.icoYokai2 = new ImageIcon(getClass().getResource("/Image/imgRouge.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYokai2 = this.icoYokai2.getImage(); // on associe ico a imagebandefond

        this.icoYokai3 = new ImageIcon(getClass().getResource("/Image/imgVert.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYokai3 = this.icoYokai3.getImage(); // on associe ico a imagebandefond

        this.icoYokai4 = new ImageIcon(getClass().getResource("/Image/imgBleu.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgYokai4 = this.icoYokai4.getImage(); // on associe ico a imagebandefond
    }

    //METHODES
    public void paintComponent(Graphics g){
        g.drawImage(this.imgBandeFond, 0, 0, null);
        g.drawImage(this.imgYokai1, this.LARGEUR_YOKAI, 0, null);
        g.drawImage(this.imgYokai2, 0, 0, null);
        g.drawImage(this.imgYokai3, this.LARGEUR_YOKAI, this.HAUTEUR_YOKAI, null);
        g.drawImage(this.imgYokai4, 0, this.HAUTEUR_YOKAI, null);
    }
}
