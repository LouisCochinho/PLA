/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.decor;

import pla.action.transition.*;


/**
 *
 * @author antoi
 */
public class Mur extends Decor {
    
    public Mur() {
    	super(DecorSprite.MUR, 64, 64, 5);
        ajouterAction(new Construire());
        ajouterAction(new Demolir());
    } 
}
