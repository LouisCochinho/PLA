package pla;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel{
	
	private Case cases[];
	
	
	public void paintComponent(Graphics g){		
		for(int i = 0;i<1000;i+=20){	
			for(int j = 0;j<49;j++){
				this.add(new Case(g, j*20,i));
			}
		}
	}
}
