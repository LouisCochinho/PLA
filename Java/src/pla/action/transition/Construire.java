/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.action.transition;

import java.util.Random;
import pla.Personnage;
import pla.decor.*;
import pla.ihm.Case;
import pla.ihm.Map;

/**
 *
 * @author antoi
 */
public class Construire extends Action_transition {

    @Override
    public void executer(Personnage p, Case c, Map m, int delta) {
        Decor d = c.getDecor();
        if(d instanceof SolNormal) {
            c.setDecor(new Muret());
        } else if(d instanceof Muret) {
            c.setDecor(new Mur());
        } else if(d instanceof Mur) {
            Random r = new Random();
            int i = r.nextInt(3);
            switch(i) {
                case 0:
                    c.setDecor(new BatimentNeutre());
                    break;
                case 1:
                    c.setDecor(new Gendarmerie());
                    break;
                case 2:
                    c.setDecor(new Skatepark());
                    break;
                default:
                    break;
            }
        }
    }
    
}
