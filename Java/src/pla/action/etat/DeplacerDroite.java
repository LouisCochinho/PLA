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
		float currdepl = 0.1f*delta;
		float newPos = (p.getX()+currdepl)%modulo_tore_x;
		p.setX(newPos);
		p.setDeplacementCourant(p.getDeplacementCourant()+currdepl);
	}

	@Override
	protected void executer(Personnage p, int delta) {
		// TODO Auto-generated method stub
		
	}
}
