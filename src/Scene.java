import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;


public class Scene extends JPanel {


    //VARIABLES
    private ImageIcon icoBandeFond;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgBandeFond;

    private ImageIcon icoDosIndice;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    private Image imgDosIndice;




    private final int LARGEUR_YOKAI = 1347;  //final car constt
    private final int HAUTEUR_YOKAI = 707;  //final car constt

    //CONSTRUCTEUR
    //creation obj de type scene avec constructeur
    public Scene() {

        super(); // appel aux constructeur de la super classe
        //instancier ico et img
        this.icoBandeFond = new ImageIcon(getClass().getResource("/Image/redBackground.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgBandeFond = this.icoBandeFond.getImage(); // on associe ico a imagebandefond

        this.icoDosIndice = new ImageIcon(getClass().getResource("/Image/img_1.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        this.imgDosIndice = this.icoDosIndice.getImage(); // on associe ico a imagebandefond


    }

    //METHODES
    public void paintComponent(Graphics g){
        g.drawImage(this.imgBandeFond, 0, 0, null);
        g.drawImage(this.imgDosIndice, 150, 150, null);





       // for (int i=1; i<nAlibiFC;i++){
            //if(nAlibiFC !=0){
            //    g.drawImage(this.imgDosIndice,1000+20*i,20*i,null);
        //    }
       // }
       // if (nAlibiFC <8){
          //  if(idJack==true){
             //   g.drawImage(tabShuffleAlibi[nAlibi].getImgAlibi(),1140,200,null);
            //}
        //}

                //comment s'eppel notre liste de carte mélangée
           //     variable qui compte le nombre de carte face cahee
             //   n alibi le  numero de la derniere carte

    }
}
