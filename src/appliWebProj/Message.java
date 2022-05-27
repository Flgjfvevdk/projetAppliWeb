package appliWebProj;
import javax.persistence.*;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String texte;
	
	public Message() {
		
	}
	
	public Message(String messages) {
		texte = messages;
	}
	
}
