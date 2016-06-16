/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla;

import org.newdawn.slick.Color;

/**
 *
 * @author antoi
 */
public enum TypePersonnage {
    ROUGE("res/thugRouge.png", Color.red, false),
    BLEU("res/thugBleu.png", Color.blue, true),
    BERNARD("res/Bernard.png", Color.yellow, false);
    
    private final String ref;
    private final Color color;
    private final boolean inverse;
    
    private TypePersonnage(String ref, Color color, boolean inverse) {
        this.ref = ref;
        this.color = color;
        this.inverse = inverse;
    }

    public String getRef() {
        return ref;
    }

    public Color getColor() {
        return color;
    }

    public boolean isInverse() {
        return inverse;
    }
}
