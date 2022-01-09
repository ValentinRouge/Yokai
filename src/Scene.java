
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;


public class Scene extends JPanel {


    //VARIABLES
    private ImageIcon icoBandeFond;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgBandeFond;


    //CONSTRUCTEUR
    //creation obj de type scene avec constructeur
    public Scene() {

        super(); // appel aux constructeur de la super classe
        //instancier ico et img
        this.icoBandeFond = new ImageIcon(getClass().getResource("/Image/redBackground.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgBandeFond = this.icoBandeFond.getImage(); // on associe ico a imagebandefond
    }

    //METHODES
    public void paintComponent(Graphics g){
        g.drawImage(this.imgBandeFond, 0, 0, null);

    }
}
