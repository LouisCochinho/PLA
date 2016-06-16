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
		float tmp = p.getX();
		float depl = (p.getX()-0.1f*delta)%modulo_tore_x;
		p.setX(depl);
		p.setDeplacementCourant(p.getDeplacementCourant()+Math.abs(depl-tmp));
		if(p.getX() < 0){
			p.setX(modulo_tore_x-32);
			p.setDeplacementCourant(0);
		}
		
		/* p.setDirection(1);			
		float currdepl = 0.1f*delta;
		float newPos = (p.getX()-currdepl);
		System.out.println("posX = "+p.getX());
		System.out.println("currdepl = "+currdepl);
		System.out.println("newPos  1 = "+newPos);
		if(newPos < 0){
			newPos+=modulo_tore_x;
		}
		System.out.println("newPos 2= "+newPos);
		p.setY(newPos);
		p.setDeplacementCourant(p.getDeplacementCourant()+currdepl);
		*/		
	}

	@Override
	protected void executer(Personnage p, int delta) {
		// TODO Auto-generated method stub
		
	}
}
