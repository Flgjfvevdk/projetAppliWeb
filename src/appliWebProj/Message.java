package appliWebProj;
import javax.persistence.*;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String texte;
	
	@ManyToOne
	Compte proprietaire;
	
	@ManyToOne(fetch = FetchType.EAGER)
	Publication publication;
	
	@ManyToOne(fetch = FetchType.EAGER)
	Topic topic;
	
	public Message() {
	}
	
	public Message(String messages, Compte c, Publication p) {
		texte = messages;
		this.proprietaire = c;
		this.publication = p;
	}
	public Message(String messages, Compte c, Topic t) {
		texte = messages;
		this.proprietaire = c;
		this.topic = t;
	}
	
	public String getTexte() {
		return this.texte;
	}
	
	public Compte getCreateur() {
		return this.proprietaire;
	}
	
}
