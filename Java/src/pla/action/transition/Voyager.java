/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.action.transition;

import java.util.ArrayList;
import java.util.Random;
import pla.Jeu;
import pla.Personnage;
import pla.decor.*;
import pla.ihm.Case;
import pla.ihm.Map;

/**
 *
 * @author antoi
 */
public class Voyager extends Action_transition {

    @Override
    public void executer(Personnage p, Case c, Jeu j, int delta) {
        Decor d = c.getDecor();
        if(d instanceof BoucheEgout) {
            ArrayList<Case> cases = new ArrayList<Case>();
            for (Case[] case1 : j.getMap().getCases()) {
                for (Case case11 : case1) {
                    if((case11.getIndexI() != c.getIndexI() || case11.getIndexJ() != c.getIndexJ()) && case11.getDecor() instanceof BoucheEgout) {
                        cases.add(case11);
                    }
                }
            }
            if(!cases.isEmpty()) {
                Random r = new Random();
                int i = r.nextInt(cases.size());
                Case cc = cases.get(i);
                p.setX(cc.getIndexJ() * 64 + 32);
                p.setY(cc.getIndexI() * 64 + 32);
            }
        }
    }
    
}
