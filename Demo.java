

import java.awt.EventQueue;

/* ceci est un changement pour demontrer la fonction de Git
*/

/**
 * Démonstration de l'architecture MVC avec un programme simple.
 * Les outils du Java API java.beans.PropertyChangeSupport
 * sont utilisés.
 * 
 * @author Vincent Lacasse
 *
 */

public class Demo {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void createAndShowGUI() throws Exception {
		
		// Création du modèle. Il n'y a qu'une seule instance de la classe
		// MonModele dans cette application.
		MonModele monModele = new MonModele();
		
		// Création de la première vue.
		new MaVue(monModele);
	}
}
