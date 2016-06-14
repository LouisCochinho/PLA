package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerDroite extends Action_etat {

	@Override
	public void executer(Personnage p, int delta,int modulo_tore_x,int modulo_tore_y) {
		p.setDirection(3);	
		float tmp = p.getX();
		float depl = (p.getX()+0.1f*delta)%modulo_tore_x;
		p.setX(depl);
		p.setDeplacementCourant(p.getDeplacementCourant()+Math.abs(depl-tmp));
	}

	@Override
	protected void executer(Personnage p, int delta) {
		// TODO Auto-generated method stub
		
	}
}
