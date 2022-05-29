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
	
	public Carte getCarte(int id) {
		ArrayList<Carte> listeCartes = new ArrayList<Carte>(getListeCartes());
		return listeCartes.get(id);
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
	
	public Collection<Message> getListeMessages(int idPublication){
		String requete = "SELECT m FROM Message m WHERE m.publication.id='"+idPublication+"'";
		TypedQuery<Message> req = em.createQuery(requete,Message.class);
		return req.getResultList();
	}
	public Publication getPublication(int idPublication){
		String requete = "SELECT p FROM Publication p WHERE p.id='"+idPublication+"'";
		TypedQuery<Publication> req = em.createQuery(requete,Publication.class);
		if(req.getResultList().size() > 0) {
			return ((ArrayList<Publication>) req.getResultList()).get(0);
		}
		return null;
	}
	public void ajouterCommentairePublication(int idPublication, String usernameCommentateur, String commentaire) {
		String requete = "SELECT p FROM Publication p WHERE p.id='"+idPublication+"'";
		TypedQuery<Publication> req = em.createQuery(requete,Publication.class);
		if(req.getResultList().size() > 0) {
			Publication publication = ((ArrayList<Publication>) req.getResultList()).get(0);
			Message m = new Message(commentaire, getCompte(usernameCommentateur), publication);
			em.persist(m);
		}
	}
	public Collection<Deck> getListeDeck(String username){
		String requete = "SELECT d FROM Deck d WHERE d.proprietaire.nom='"+username+"'";
		TypedQuery<Deck> rq = em.createQuery(requete, Deck.class);
		return rq.getResultList();
	}
	
	public void ajouterDeck(String username, String nom) {
		Compte cp =  this.getCompte(username);
		Deck d = new Deck(nom, cp);
		em.persist(cp);
		em.persist(d);
	}
	public void ajouterCarteADeck(int idCarte, int idDeck) {
		String requete = "SELECT d FROM Deck d WHERE d.id='"+idDeck+"'";
		TypedQuery<Deck> rq = em.createQuery(requete, Deck.class);
		Deck d = rq.getResultList().get(0);
		String requete2 = "SELECT c FROM Carte c WHERE c.id='"+idCarte+"'";
		TypedQuery<Carte> rq2 = em.createQuery(requete2, Carte.class);
		Carte carte = rq2.getResultList().get(0);
		d.AddCarte(carte);
		em.persist(d);
		em.persist(carte);
	}
	public void creerTopic(String titre, String usernameCreateur) {
		Compte createur = getCompte(usernameCreateur);
		Topic t = new Topic(titre, createur);
		em.persist(t);
		em.persist(createur);
	}
	
	public Collection<Topic> getAllTopics(){
		TypedQuery<Topic> req = em.createQuery("select t from Topic t",Topic.class);
		return req.getResultList();
	}
	public Topic getTopic(int idTopic){
		String requete = "SELECT t FROM Topic t WHERE t.id='"+idTopic+"'";
		TypedQuery<Topic> req = em.createQuery(requete,Topic.class);
		if(req.getResultList().size() > 0) {
			return ((ArrayList<Topic>) req.getResultList()).get(0);
		}
		return null;
	}
	public void ajouterCommentaireTopic(int idTopic, String usernameCommentateur, String commentaire) {
		String requete = "SELECT t FROM Topic t WHERE t.id='"+idTopic+"'";
		TypedQuery<Topic> req = em.createQuery(requete,Topic.class);
		if(req.getResultList().size() > 0) {
			Topic topic = ((ArrayList<Topic>) req.getResultList()).get(0);
			Message m = new Message(commentaire, getCompte(usernameCommentateur), topic);
			em.persist(m);
		}
	}
	public Collection<Message> getListeMessagesTopic(int idTopic){
		String requete = "SELECT m FROM Message m WHERE m.topic.id='"+idTopic+"'";
		TypedQuery<Message> req = em.createQuery(requete,Message.class);
		return req.getResultList();
	}
}
