package appliWebProj;

import javax.persistence.*;

@Entity
public class Carte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String nom;
	String image;
	
	@OneToOne(mappedBy="carte")
	Publication publication;
	
	
	public Carte() {
	}
	
	public Carte(String nom, String image) {
		this.nom = nom;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	

}
