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
	
	@OneToMany(mappedBy = "proprietaire")
	List<Message> messages;
	
	public Compte() {
	}
	
	public Compte(String nom, String mdp) {
		this.nom = nom;
		this.motDePasse = mdp;
		this.argent = 10;
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
	
	public List<Deck> getDecks() {
		return this.decks;
	}
	
	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	public void addDeck(Deck d) {
		decks.add(d);
	}
	
	public List<Carte> getCartes(){
		return this.cartes;
	}
	
	public void addArgent(int m) {
		this.argent += m;
	}
}
