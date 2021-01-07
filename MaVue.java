

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Vue de l'application.
 * MaVue implémente l'interface Observer du Java API afin qu'elle puisse
 * s'enregistrer auprès de MonModele. Le mécanisme observateur-sujet permet
 * à plusieurs vues (observatrices) de s'enregistrer à un modèle (sujet).
 * 
 * De plus, le constructeur de MaVue instancie le contrôleur MonControleur. 
 * MaVue délègue le traitement des événements qu'elle génère 
 * (par ex. clique sur un bouton) au controleur MonControleur. 
 * Dans le cas présent, il y a une instance de MonControleur pour 
 * chaque instance de MaVue. 
 * 
 * @author Vincent Lacasse
 *
 */
@SuppressWarnings("serial")
public class MaVue extends JFrame implements PropertyChangeListener {

	public static final String ACTION_TEXTE = "AT"; 
	public static final String NOUVELLE_FENETRE = "NF";
	public static final String CHANGER_COULEUR = "CC";
	
	private JPanel contentPane;
	private JTextField txtMonTexte;
	private MonModele monModele;
	private static int compteurFenetre = 1;

	/**
	 * Create the frame.
	 */
	public MaVue(MonModele monModele) {
		
		super("Exemple MVC " + compteurFenetre++);
		this.monModele = monModele;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		
		// Placer la fenetre dans un endroit aléatoire sur l'écran
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		int positionX = (int)(Math.random() * (screenSize.width - 450));
		int positionY = (int)(Math.random() * (screenSize.height - 100));
		setLocation(positionX, positionY);
		
		// Créer un JPanel qui contiendra le JTextField et les JButton.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		// Créer un JTextArea et l'ajouter au JPanel
		txtMonTexte = new JTextField();
		txtMonTexte.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		txtMonTexte.setColumns(10);
		contentPane.add(txtMonTexte);
		
		 // L'appel de propertyChange ici force l'écran à se redessiner.
		propertyChange(null);
		
		// Créer les JButton est les ajouter au JPanel
		JButton btnNouvelleFenetre = new JButton("Nouvelle Fenetre");
		contentPane.add(btnNouvelleFenetre);
		
		JButton btnChangerCouleur = new JButton("Changer Couleur");
		contentPane.add(btnChangerCouleur);
		
		/*
		 * Enregistrer cette vue à la liste des observateurs du modèle
		 */
		monModele.addPropertyChangeListener(this);
		
		/*
		 * Créer du controleur de cette vue.
		 */
		MonControleur monControleur = new MonControleur(this, monModele);

		/*
		 * On inscrit le controleur comme ActionListener auprès du JTextField,
		 * et des deux JButton. De cette façon, la méthode actionPerformed() du 
		 * controleur sera appelée lorsque l'usager:
		 *   - presse la touche "enter" dans la zone de texte
		 *   - presse un des boutons
		 */
		txtMonTexte.setActionCommand(ACTION_TEXTE);
		txtMonTexte.addActionListener(monControleur);
		
		btnNouvelleFenetre.setActionCommand(NOUVELLE_FENETRE);
		btnNouvelleFenetre.addActionListener(monControleur);
		
		btnChangerCouleur.setActionCommand(CHANGER_COULEUR);
		btnChangerCouleur.addActionListener(monControleur);
		
		// Afficher cette Vue
		setVisible(true);	
		
		// Mettre le focus sur le bouton Nouvelle Fenetre
		btnNouvelleFenetre.requestFocus();	
	}
	
	public String getLeTexte() {
		return txtMonTexte.getText();
	}

	/*
	 * La méthode propertyChange() fait partie de l'interface PropertyChangeListener
	 * Elle doit être implémentée par cette classe. 
	 * propertyChange() est appelée par le modèle lorsque l'état du modèle est modifié. 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String texte = monModele.getLeTexte();
		Color couleur = monModele.getLaCouleur();
		txtMonTexte.setText(texte);
		txtMonTexte.setForeground(couleur);		
	}
}
