/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla;

import java.util.ArrayList;
import pla.action.transition.*;
import pla.decor.*;

/**
 *
 * @author antoi
 */
public class Association {
    private static ArrayList<Action_transition> actions;
    private static ArrayList<Decor> decors;
    
    static {
        actions = new ArrayList<Action_transition>();
        decors = new ArrayList<Decor>();
        init();
    }
    
    private static void init() {
        addAssociation(new Admirer(), new BatimentNeutre());
        addAssociation(new Combattre(), new BombeEau());
        addAssociation(new Construire(), new BombePeinture());
        addAssociation(new Demolir(), new BoucheEgout());
        addAssociation(new Dupliquer(), new Gendarmerie());
        addAssociation(new LaisserTomber(), new Mur());
        addAssociation(new PeindreAmi(), new Muret());
        addAssociation(new PeindreEnnemi(), new Skatepark());
        addAssociation(new PeindreNeutre(), new SolAmi());
        addAssociation(new Prendre(), new SolEnnemi());
        addAssociation(new Voler(), new SolNormal());
        addAssociation(new Voyager(), new Velo());
    }
    
    private static void addAssociation(Action_transition a, Decor d) {
        actions.add(a);
        decors.add(d);
    }

    public static Action_transition getAction(Decor decor) {
        for(int i=0; i<decors.size(); i++) {
            if(decor.getClass() == decors.get(i).getClass()) {
                return actions.get(i);
            }
        }
        return null;
    }

    public static Decor getDecor(Action_transition action) {
        for(int i=0; i<actions.size(); i++) {
            if(action.getClass() == actions.get(i).getClass()) {
                return decors.get(i);
            }
        }
        return null;
    }
}
