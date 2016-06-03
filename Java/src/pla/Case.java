package pla;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Case extends JPanel{
	private ArrayList<Personnage> personnages;
	private Decor decor;
	
	
	
	public Case(Graphics g,int posX, int posY){
		g.drawRect(posX, posY, 20, 20);
	}
}
