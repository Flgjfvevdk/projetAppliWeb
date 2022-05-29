package appliWebProj;

import java.util.List;

import javax.persistence.*;

@Entity
public class Deck {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	
	@ManyToMany(mappedBy="decks",fetch = FetchType.EAGER);
	List<Carte> cartes;
	
	public Deck(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Carte> getCartes() {
		return cartes;
	}
	public void setCartes(List<Carte> cs) {
		this.cartes = cs;
	}	
	public void AddCarte(Carte c) {
		cartes.add(c);
	}
	public void SuprCarte(Carte c) {
			cartes.remove(c);
	}
}
