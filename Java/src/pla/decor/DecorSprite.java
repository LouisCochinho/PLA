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
    BATIMENT_NEUTRE(0, 11),
    BOMBE_EAU(0, 7),
    BOMBE_PEINTURE(0, 6),
    BOUCHE_EGOUT(0, 4),
    GENDARMERIE(0, 1),
    HELICO(0, 1),
    MUR(0, 0),
    MURET(0, 5),
    SKATEPARK(0, 2),
    SOL_AMI(0, 3),
    SOL_ENNEMI(0, 10),
    SOL_NORMAL(0, 9),
    VELO(0, 8);
    
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
