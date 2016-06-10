package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerDroite extends Action_etat {

	@Override
	public void executer(Personnage p, int delta) {
		p.setDirection(3);
		//p.setX((p.getX()+0.5f*delta)%MODULO_TORE_X);
		p.setX((p.getX()+64)%MODULO_TORE_X);
	}

}
