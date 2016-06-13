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

/**
 *
 * @author antoi
 */
public class Demolir extends Action_transition {

    @Override
    public void executer(Personnage p, Case c, int delta) {
        Decor d = c.getDecor();
        if(d instanceof BatimentNeutre || d instanceof Gendarmerie || d instanceof Skatepark) {
            c.setDecor(new Mur());
        } else if(d instanceof Mur) {
            c.setDecor(new Muret());
        } else if(d instanceof Muret) {
            c.setDecor(new SolNormal());
        }
    }
    
}
