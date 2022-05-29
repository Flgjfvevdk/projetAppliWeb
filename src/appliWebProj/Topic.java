package appliWebProj;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@ManyToOne
	Compte proprietaire;
	
	@OneToMany(mappedBy="topic")
	List<Message> messages;
	
	String titre;
	
	public Topic(){
		
	}
	public Topic(String titre, Compte p) {
		this.titre = titre;
		this.proprietaire = p;
		this.messages = new ArrayList<Message>();
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Compte getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Compte proprietaire) {
		this.proprietaire = proprietaire;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void ajouterMessage(Message mess) {
		this.messages.add(mess);
	}
	
	public String getNomCreateur() {
		return this.proprietaire.getNom();
	}
}
