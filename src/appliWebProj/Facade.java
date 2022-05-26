package appliWebProj;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.*;

@Singleton
public class Facade {
	@PersistenceContext
	private EntityManager em;
	
	public void creerCarte(String nom) {
		Carte c = new Carte(nom);
		em.persist(c);
	}
	
	public Collection<Carte> getListeCartes(){
			
		TypedQuery<Carte> req = em.createQuery("select c from Carte c",Carte.class);
		return req.getResultList();
		
	}
}
