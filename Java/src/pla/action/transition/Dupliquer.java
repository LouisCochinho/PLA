/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.action.transition;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import pla.Jeu;
import pla.Personnage;
import pla.ihm.Case;
import pla.ihm.Map;

/**
 *
 * @author antoi
 */
public class Dupliquer extends Action_transition {

    @Override
    public void executer(Personnage p, Case c, Jeu j, int delta) {
        try {
            Personnage newP = new Personnage(p.getTypePersonnage(), p.getDirection(), (int)p.getwSprite(), (int)p.gethSprite(), p.getAutomate());
            j.ajouterPersonnage(newP);
            newP.init();
        } catch (SlickException ex) {
            Logger.getLogger(Dupliquer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
