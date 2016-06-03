package pla;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	
	public Fenetre(){
		//Définit un titre pour votre fenêtre
				this.setTitle("Ma première fenêtre java");

				//Définit une taille pour celle-ci ; ici, 400 px de
				this.setSize(1000, 1000);

				//Nous allons maintenant dire à notre objet de se positionner au centre
				this.setLocationRelativeTo(null);

				//Terminer le processus lorsqu'on clique sur fermer 
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
			
				//On prévient notre JFrame que ce sera notre JPanel qui sera son contentPane
				this.setContentPane(new Map());
				
				// Faire afficher la fenetre
				this.setVisible(true);
	}
}
