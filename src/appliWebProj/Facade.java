package appliWebProj;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.ejb.Singleton;
import javax.persistence.*;

@Singleton
public class Facade {
	@PersistenceContext
	private EntityManager em;
	
	public void creerCarte(String nom, String image, String usernameCreateur) {
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+usernameCreateur+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		Compte createur = rq.getResultList().get(0);
		Carte c = new Carte(nom,image);
		Publication p = new Publication(c, createur);
		em.persist(c);
		em.persist(p);
		
	}
	
	public Compte creerCompte(String username, String mdp){
		
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		if(rq.getResultList().size() == 0){
			Compte c = new Compte(username, mdp);
			em.persist(c);
			return c;
		}
		return null;
	}
	
	public Compte connexionValide(String username, String mdp) {
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		if(rq.getResultList().size() == 1 && rq.getResultList().get(0).getMotDePasse().equals(mdp)){
			return rq.getResultList().get(0);
		}
		return null;
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
	
	public Collection<Carte> getPlusieursCartes(int nbCartes, String username){
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		Compte compteActif = rq.getResultList().get(0);
		
		Collection<Carte> cartesRenvoies = new ArrayList<Carte>();
		for(int k = 0; k < nbCartes; k++) {
			Carte carte = getRandomCarte();
			cartesRenvoies.add(carte);
			compteActif.ajouterCarte(carte);
		}
		em.persist(compteActif);
		return cartesRenvoies;
	}
	
	public Collection<Publication> getPublications(){
		TypedQuery<Publication> req = em.createQuery("select p from Publication p",Publication.class);
		return req.getResultList();
	}
	
	public void upVotePublication(int id, String usernameUpvoter) {
		TypedQuery<Publication> req = em.createQuery("select p from Publication p WHERE p.id='"+id+"'",Publication.class);
		System.out.println("\n\n" + req.getResultList().size() + "\n\n");
		ArrayList<Publication> resultat = (ArrayList<Publication>)req.getResultList();
		if(resultat.size() > 0) {
			resultat.get(0).incrementVotes(usernameUpvoter);
			//resultat.get(0).incrementVotes();
		} else {
			System.out.println("\nErreur dans la procédure d'upvote\n");
		}
	}
	
	
	public Compte getCompte(String username){
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		Compte c = rq.getResultList().get(0);
		
		return c;
	}
	public void ajouterArgentCompte(String username, int valeur) {
		String requete = "SELECT c FROM Compte c WHERE c.nom='"+username+"'";
		TypedQuery<Compte> rq = em.createQuery(requete, Compte.class);
		if(rq.getResultList().size() > 0) {
			Compte c = rq.getResultList().get(0);
			c.addArgent(valeur);
			em.persist(c);
		}
	}
}
