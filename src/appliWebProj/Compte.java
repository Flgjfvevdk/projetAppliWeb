package appliWebProj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
public class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	String motDePasse;
	
	int argent;
	
	@ManyToMany(fetch = FetchType.EAGER)
	List<Carte> cartes;
	@OneToMany
	List<Deck> decks;
	
	
	@OneToMany(mappedBy = "proprietaire")
	List<Publication> publications;
	
	@OneToMany
	List<Message> messages;
	
	public Compte() {
	}
	
	public Compte(String nom, String mdp) {
		this.nom = nom;
		this.motDePasse = mdp;
		this.argent = 0;
		this.cartes = new ArrayList<Carte>();
		this.decks = new ArrayList<Deck>();
	}
	
	public void ajouterCarte(Carte c) {
		if(cartes.contains(c)) {
			argent ++;
		}else {
			cartes.add(c);
		}
		
	}
	public String getMotDePasse() {
		return this.motDePasse;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getArgent() {
		return this.argent;
	}
	
	public List<Carte> getCartes(){
		return this.cartes;
	}
}
