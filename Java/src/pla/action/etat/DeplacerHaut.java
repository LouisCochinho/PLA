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
		//p.setY((p.getY()-0.5f*delta)%MODULO_TORE_Y);
		p.setY((p.getY()-64)%MODULO_TORE_Y);
		if(p.getY() < 0){
			p.setY(MODULO_TORE_Y - 1);
		}   			
	}

}
