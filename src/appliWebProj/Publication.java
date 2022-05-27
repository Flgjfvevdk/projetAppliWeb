package appliWebProj;
import javax.persistence.*;

@Entity
public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@OneToOne
	Carte carte;
	
	
	@ManyToOne
	Compte proprietaire;
	
	
	int votes;
	
	public Publication() {
		
	}
	public Publication(Carte c) {
		this.carte = c;
	}
	
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Carte getCarte() {
		return this.carte;
	}
	public String getNomCarte() {
		return this.carte.getNom();
	}
	

	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public void incrementVotes() {
		this.votes ++;
	}
	
}
