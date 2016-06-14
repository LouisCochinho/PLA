package pla.action.etat;

import pla.Personnage;
import pla.action.etat.Action_etat;

/**
 *
 * @author antoi
 */
public class DeplacerGauche extends Action_etat {

	@Override
	public void executer(Personnage p, int delta,int modulo_tore_x,int modulo_tore_y) {
		p.setDirection(1);		
		float depl = (p.getX()-0.1f*delta)%modulo_tore_x;
		p.setX(depl);
		if(p.getX() < 0){
			p.setX(modulo_tore_x-32);
		}
	}

	@Override
	protected void executer(Personnage p, int delta) {
		// TODO Auto-generated method stub
		
	}
}
