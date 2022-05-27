package appliWebProj;

import java.util.List;

import javax.persistence.*;

@Entity
public class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	
	int argent;
	
	@ManyToMany
	List<Carte> cartes;
	@OneToMany
	List<Deck> decks;
	
	
	@OneToMany(mappedBy = "proprietaire")
	List<Publication> publications;
	
	@OneToMany
	List<Message> messages;
	
	public Compte() {
	}
	
	public Compte(String nom) {
		this.nom = nom;
		this.argent = 0;
	}
}
