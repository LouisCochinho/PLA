/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.action.transition;

import pla.Personnage;
import pla.decor.*;
import pla.ihm.Case;

/**
 *
 * @author antoi
 */
public class PeindreNeutre extends Action_transition {

    @Override
    public void executer(Personnage p, Case c, int delta) {
        Decor d = c.getDecor();
        if(d instanceof SolAmi || d instanceof SolEnnemi) {
            c.setDecor(new SolNormal());
        }
    }
    
}
