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
	
	public void creerCarte(String nom, String image) {
		Carte c = new Carte(nom,image);
		Publication p = new Publication(c);
		em.persist(c);
		em.persist(p);
	}
	
	public boolean creerCompte(String username, String mdp){
		
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		if(rq.getResultList().size() == 0){
			Compte c = new Compte(username, mdp);
			em.persist(c);
			return true;
		}
		return false;
	}
	
	public boolean connexionValide(String username, String mdp) {
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		if(rq.getResultList().size() == 1 && rq.getResultList().get(0).getMotDePasse().equals(mdp)){
			return true;
		}
		return false;
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
