/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import pla.Automate;
import pla.Jeu;

/**
 *
 * @author antoi
 */
public class TestAutomate {
    
    // conteneur qui permet de configurer dans quel contexte va tourner notre
    // jeu et lui dire quznd d�marrer, s'arreter, etc.
    static private AppGameContainer app;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        // Ce conteneur s'instancie en passant un objet impl�mentant Game (dont
        // BasicGame) comme notre fenetre, � son constructeur
        app = new AppGameContainer(new Jeu("Thug tag"));
        // Sp�cifie le mode d'affichage : le deuxieme parametre est le mode
        // plein ecran ou non
        app.setDisplayMode(640, 480, true);
        // D�marre le jeu
        app.start();
        Automate a = new Automate("../OCaml/test.xml");
    }
    
}
