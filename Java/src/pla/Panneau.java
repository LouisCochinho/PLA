package pla;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;



public class Panneau extends JPanel {
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(20, 20, 75, 75);
	}
	
	
}
