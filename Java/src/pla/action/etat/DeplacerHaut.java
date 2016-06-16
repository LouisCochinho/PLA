package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerHaut extends Action_etat {

	@Override
	public void executer(Personnage p, int delta,int modulo_tore_x, int modulo_tore_y) {
		p.setDirection(0);		
		float currdepl = 0.1f*delta;
		float newPos = (p.getY()-currdepl);
		if(newPos < 0){
			newPos+=modulo_tore_y;
		}
		p.setY(newPos);
		p.setDeplacementCourant(p.getDeplacementCourant()+currdepl);  			
	}

	@Override
	protected void executer(Personnage p, int delta) {
		// TODO Auto-generated method stub
		
	}

}
