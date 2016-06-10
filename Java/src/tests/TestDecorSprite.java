/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import pla.decor.DecorSprite;

/**
 *
 * @author antoi
 */
public class TestDecorSprite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DecorSprite d = DecorSprite.BOMBE_EAU;
        System.out.println(d.getX() + "," + d.getY());
    }
    
}
