/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.action.transition;

import pla.Personnage;
import pla.decor.*;
import pla.ihm.Case;
import pla.ihm.Map;

/**
 *
 * @author antoi
 */
public class Prendre extends Action_transition {

    @Override
    public void executer(Personnage p, Case c, Map m, int delta) {
        Decor d = c.getDecor();
        if(d instanceof BombeEau || d instanceof BombePeinture) {
            c.setDecor(new SolNormal());
        }
    }
    
}
