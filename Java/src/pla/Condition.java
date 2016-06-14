/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla;

import java.util.ArrayList;
import pla.decor.Decor;
import pla.decor.SolAmi;
import pla.decor.SolEnnemi;
import pla.ihm.Case;
import pla.ihm.Map;

/**
 *
 * @author antoi
 */
public class Condition {
    private final ArrayList<ConditionSimple> conditions;
    
    public Condition() {
        conditions = new ArrayList<ConditionSimple>();
    }
    
    public void addCondition(ConditionSimple c) {
        conditions.add(c);
    }
    
    public int nombreConditions() {
        return conditions.size();
    }
    
    public ConditionSimple getCondition(int i) {
        return conditions.get(i);
    }
    
    @Override
    public String toString() {
        String s = "";
        if(conditions.size() > 0) {
            s = conditions.get(0).toString();
            for(int i=1; i<conditions.size(); i++) {
                s += " && " + conditions.get(i);
            }
        }
        return s;
    }
    
	public ArrayList<ConditionSimple> getConditions() {
		return conditions;
	}
	
    public boolean estVerifiee(Personnage p,Map m){
	for (ConditionSimple cs : this.conditions){
		Case caseCourante = m.getCaseFromCoord((int)p.getX(),(int)p.getY());
		Case caseOrientee = m.getCase(caseCourante,cs.getCellule());
		if(!cs.estVerifiee(caseOrientee.getDecor())){
			return false;
		}
	}
	return true;
    }
    
    public void inverser() {
        for(ConditionSimple c : conditions) {
            if(c.getDecor() instanceof SolAmi) {
                c.setDecor(new SolEnnemi());
            } else if(c.getDecor() instanceof SolEnnemi) {
                c.setDecor(new SolAmi());
            }
        }
    }
}
