/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.decor;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import pla.action.transition.*;

/**
 *
 * @author antoi
 */
public class BatimentNeutre extends Decor {
    
    public BatimentNeutre() {
    	super(DecorSprite.BATIMENT_NEUTRE, 64, 64);
        ajouterAction(new Demolir());
    }
    
}
