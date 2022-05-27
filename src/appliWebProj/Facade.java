package appliWebProj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.ejb.Singleton;
import javax.persistence.*;

@Singleton
public class Facade {
	@PersistenceContext
	private EntityManager em;
	
	public void creerCarte(String nom) {
		Carte c = new Carte(nom);
		Publication p = new Publication(c);
		em.persist(c);
		em.persist(p);
	}
	
	public Collection<Carte> getListeCartes(){
			
		TypedQuery<Carte> req = em.createQuery("select c from Carte c",Carte.class);
		return req.getResultList();
	}
	
	public Carte getRandomCarte() {
		ArrayList<Carte> listeCartes = new ArrayList<Carte>(getListeCartes());
		Random rand = new Random();
		int indexRandom = rand.nextInt(listeCartes.size());
		return listeCartes.get(indexRandom);
	}
	
	public Collection<Carte> getPlusieursCartes(int nbCartes){
		Collection<Carte> cartesRenvoies = new ArrayList<Carte>();
		for(int k = 0; k < nbCartes; k++) {
			Carte carte = getRandomCarte();
			cartesRenvoies.add(carte);
		}
		return cartesRenvoies;
	}
	
	public Collection<Publication> getPublications(){
		TypedQuery<Publication> req = em.createQuery("select p from Publication p",Publication.class);
		return req.getResultList();
	}
}
