package appliWebProj;

import javax.persistence.*;

@Entity
public class Carte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	
	@OneToOne(mappedBy="carte")
	Publication publication;
	
	
	public Carte() {
	}
	
	public Carte(String nom) {
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
	

}
