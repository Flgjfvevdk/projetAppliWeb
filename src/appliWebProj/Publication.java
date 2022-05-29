package appliWebProj;


import java.util.ArrayList;
import java.util.List;

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
	
	@OneToMany(mappedBy="publication")
	List<Message> messages;
	
	ArrayList<String> usernameComptesUpvote;
	
	
	int votes;
	
	public Publication() {
		
	}
	public Publication(Carte c, Compte p) {
		this.carte = c;
		this.proprietaire = p;
		usernameComptesUpvote = new ArrayList<String>();
		this.messages = new ArrayList<Message>();
	}
	
	public int getId() {
		return this.id;
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
	public void incrementVotes(String usernameUpvoter) {
		this.votes ++;
		this.proprietaire.addArgent(1);
		this.usernameComptesUpvote.add(usernameUpvoter);
	}
	
	public boolean userAlreadyUpvote(String username) {
		return this.usernameComptesUpvote.contains(username);
	}
	
	public String getNomCreateur() {
		return this.proprietaire.getNom();
	}
	
	public void ajouterMessage(Message mess) {
		this.messages.add(mess);
	}
	public List<Message> getMessages() {
		return this.messages;
	}
	
	
}
