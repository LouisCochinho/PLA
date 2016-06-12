package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerBas extends Action_etat {

	@Override
	public void executer(Personnage p, int delta) {
		p.setDirection(2);
		float depl = (p.getY()+0.1f*delta)%MODULO_TORE_Y;
		p.setY(depl);
	}

}
