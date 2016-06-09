package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerHaut extends Action_etat {

	@Override
	public void executer(Personnage p) {
		p.setPosY((p.getPosY()-1)%MODULO_TORE_Y);
		if(p.getPosY() < 0){
			p.setPosY(MODULO_TORE_Y - 1);
		}   
	}

}
