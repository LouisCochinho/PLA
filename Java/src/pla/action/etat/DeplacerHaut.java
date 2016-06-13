package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerHaut extends Action_etat {

	@Override
	public void executer(Personnage p, int delta) {
		p.setDirection(0);		
		float depl = (p.getY()-0.1f*delta)%MODULO_TORE_Y;
		p.setY(depl);
		if(p.getY() < 0){
			p.setY(MODULO_TORE_Y - 32);
		}   			
	}

}
