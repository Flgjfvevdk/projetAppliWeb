package appliWebProj;
import java.util.List;

import javax.persistence.*;

@Entity
public class Deck {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	
	@ManyToMany
	List<Carte> cartes;
	
	public Deck() {
	}
	
	public Deck(String nom) {
		this.nom = nom;
	}
}
