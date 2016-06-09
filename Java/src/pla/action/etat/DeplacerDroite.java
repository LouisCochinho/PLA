package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerDroite extends Action_etat {

	@Override
	public void executer(Personnage p) {
		p.setPosX((p.getPosX()+1)%MODULO_TORE_X);
	}

}
