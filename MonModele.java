

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Données de l'application.
 * Dans cet exemple, il n'y a qu'une instance de la classe MonModele.
 * Sa superclasse est Observable du Java API. Elle hérite de la Classe 
 * Observable les membres et les méthodes qui permettent d'enregistrer
 * et de notifier des objets de type Observer.
 *  
 * @author Vincent Lacasse
 *
 */
public class MonModele {
	private String leTexte;	
	private Color laCouleur;
	
	// L'instance de PropertyChangeSupport permet d'implementer le patron Observer par composition.
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public MonModele() {
		leTexte = "";	
		laCouleur = Color.BLACK;	
	}

	public String getLeTexte() {
		return leTexte;
	}

	public void setLeTexte(String leTexte) {
		this.leTexte = leTexte;
		// avertir les PropertyChangeListenter (Observers) que l'état de MonModele a changé
	    pcs.firePropertyChange(null, null, null);
	}
	
	public Color getLaCouleur() {
		return laCouleur;
	}
	
	public void setLaCouleur(Color laCouleur) {
		this.laCouleur = laCouleur;
		// avertir les PropertyChangeListenter (Observers) que l'état de MonModele a changé
       pcs.firePropertyChange(null, null, null);
	}
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

}
