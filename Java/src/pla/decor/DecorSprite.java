/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pla.decor;

/**
 *
 * @author antoi
 */
public enum DecorSprite {
    BATIMENT_NEUTRE(0, 7),
    BOMBE_EAU(0, 5),
    BOMBE_PEINTURE(0, 4),
    BOUCHE_EGOUT(0, 9),
    GENDARMERIE(0, 3),
    HELICO(0, 1),
    MUR(0, 0),
    MURET(0, 1),
    SKATEPARK(0, 11),
    SOL_AMI(0, 8),
    SOL_ENNEMI(0, 10),
    SOL_NORMAL(0, 6),
    VELO(0, 2);
    
    private final int x;
    private final int y;

    private DecorSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
