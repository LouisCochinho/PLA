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
public class BombePeinture extends Decor {
    
    public BombePeinture() {
    	super(DecorSprite.BOMBE_PEINTURE, 64, 64, 1);
        ajouterAction(new Prendre());
    }
    
}
