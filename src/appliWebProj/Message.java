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
	
	public Message() {
	}
	
	public Message(String messages, Compte c, Publication p) {
		texte = messages;
		this.proprietaire = c;
		this.publication = p;
	}
	
	public String getTexte() {
		return this.texte;
	}
	
	public Compte getCreateur() {
		return this.proprietaire;
	}
	
}
