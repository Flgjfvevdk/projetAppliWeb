package appliWebProj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Serv
 */
@WebServlet("/Serv")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB
    Facade facade;
    
     int nbCartesPaquet = 5;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		System.out.println(operation);
		if(operation.equals("ongletCreationCarte")) {
			request.getRequestDispatcher("creationCarte.jsp").forward(request, response);
		}
		if(operation.equals("creerCarte")) {
			String nomCarte = request.getParameter("nomCarte");
			Part imagePart = request.getPart("imageCarte");
            
	        String nomImageCarte = imagePart.getSubmittedFileName();
	        File imagesDir = new File(System.getProperty("jboss.server.data.dir"), "imagesCarte");
	        imagesDir.mkdir();
	        
	        String[] imageSubList = nomImageCarte.split("\\.");
	        String extension = imageSubList.length>0?imageSubList[imageSubList.length-1]:"webp"; //pas beau et pas robuste
	        
	        //Collection<Part> img = request.getParts();
	        System.out.println("TEST" +  imagesDir.getPath() + "!=" + imagesDir);
	        if(!(new File(imagesDir.getPath() + "/" +nomImageCarte)).exists()) {
	                /*for (Part part : request.getParts()) {
	                	System.out.println("\n\n"+imagesDir.getPath()+ "/" + nomCarte.replaceAll("[^A-Za-z0-9]", "") + "." + extension+ "\n\n");
	                	part.write(imagesDir.getPath()+ "/" + nomCarte.replaceAll("[^A-Za-z0-9]", "") + "." + extension);
	                	System.out.println("\n\n ca marche\n\n");
	                }*/
	        		System.out.println("\n\n"+imagesDir.getPath()+ "/" + nomCarte.replaceAll("[^A-Za-z0-9]", "") + "." + extension+ "\n\n");
	                imagePart.write(imagesDir.getPath()+ "/" + nomCarte.replaceAll("[^A-Za-z0-9]", "") + "." + extension);
	                System.out.println("\n\n ca marche\n\n");
	                response.getWriter().print("The file uploaded sucessfully.");
	                    
	                String username = (String) request.getSession().getAttribute("usernameActif");
	                facade.creerCarte(nomCarte,"/appliWebProj/Images/" + nomCarte.replaceAll("[^A-Za-z0-9]", "") + "." + extension, username);
	        }
            request.getRequestDispatcher("index.html").forward(request, response); 
		}
		if(operation.equals("listerCartes")) {
			//System.out.println(facade.getListeCartes());
			request.setAttribute("liste_c", facade.getListeCartes());
			request.getRequestDispatcher("afficherCarte.jsp").forward(request, response);
		}
		if(operation.equals("acheterDeck")) {
			//String username = ((Compte) request.getSession().getAttribute("compteActif")).getNom();
			String username = (String) request.getSession().getAttribute("usernameActif");
			
			request.setAttribute("estUserConnecte", false);
			request.setAttribute("estCarteDisponible", false);
			if(username != null) {
				request.setAttribute("estUserConnecte", true);
				request.setAttribute("argentDispo", facade.getCompte(username).getArgent());
				if(facade.getListeCartes().size() > 0) {
					request.setAttribute("estCarteDisponible", true);
					if(facade.getCompte(username).getArgent() >= Carte.prixPaquet) {
						facade.ajouterArgentCompte(username, -Carte.prixPaquet);
						request.setAttribute("cartesObtenues", facade.getPlusieursCartes(nbCartesPaquet,username));
					}
				}
			}
			
			request.getRequestDispatcher("ouverturePaquetCartes.jsp").forward(request, response);
		}
		if(operation.equals("afficherPublication")) {
			request.setAttribute("listePublicat", facade.getPublications());
			System.out.println("\n\neuh\n\n");
			request.getRequestDispatcher("afficherListePublication.jsp").forward(request, response);
		}
		if(operation.equals("authentification")) {
			request.setAttribute("messageCreationCompte", "Bienvenue, vous pouvez créez un compte juste en dessous !");
			request.setAttribute("messageConnectionCompte", "Déjà inscrit ? Connectez-vous");
			request.getRequestDispatcher("authentification.jsp").forward(request, response);
		}
		if(operation.equals("creerCompte")) {
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("mdp");
			Compte compteCree;
			compteCree = facade.creerCompte(pseudo, mdp);
			if(compteCree != null) {
				HttpSession session = request.getSession();
				//session.setAttribute("compteActif", compteCree);
				session.setAttribute("usernameActif", compteCree.getNom());
				request.getRequestDispatcher("index.html").forward(request, response);
			} else {
				request.setAttribute("messageCreationCompte", "Cet username est indisponible");
				request.setAttribute("messageConnectionCompte", "Déjà inscrit ? Connectez-vous");
				request.getRequestDispatcher("authentification.jsp").forward(request, response);
			}
		}
		if(operation.equals("seConnecter")) {
			String pseudo = request.getParameter("pseudo");
			String mdp = request.getParameter("mdp");
			Compte compte;
			compte = facade.connexionValide(pseudo, mdp);
			if(compte != null) {
				HttpSession session = request.getSession();
				//session.setAttribute("compteActif", compte);
				session.setAttribute("usernameActif", compte.getNom());
				request.getRequestDispatcher("index.html").forward(request, response);
			} else {
				request.setAttribute("messageCreationCompte", "Bienvenue, vous pouvez créez un compte juste en dessous !");
				request.setAttribute("messageConnectionCompte", "Erreur de connexion, pseudo ou mdp incorrect");
				request.getRequestDispatcher("authentification.jsp").forward(request, response);
			}
		}
		if(operation.equals("seDeconnecter")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.html").forward(request, response);	
		}
		if(operation.equals("afficherPossession")) {
			//Compte c = (Compte) request.getSession().getAttribute("compteActif");
			String username = (String) request.getSession().getAttribute("usernameActif");
			if(username != null) {
				request.setAttribute("cartePossedee", facade.getCompte(username).getCartes());
				request.setAttribute("argents", facade.getCompte(username).getArgent());
				
			}
			request.getRequestDispatcher("afficherPossession.jsp").forward(request, response);
		}
		if(operation.equals("upVote")) {
			String usernameActif = (String) request.getSession().getAttribute("usernameActif");
			facade.upVotePublication(Integer.parseInt(request.getParameter("cible")), usernameActif);
			//facade.upVotePublication(Integer.parseInt(request.getParameter("cible")));
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		if(operation.equals("afficherDetailsPublication")) {
			int idPublication = Integer.parseInt(request.getParameter("cible"));
			Publication publi = facade.getPublication(idPublication);
			Collection<Message> messages = facade.getListeMessages(idPublication);
			request.setAttribute("publication", publi);
			request.setAttribute("listeMess", messages);
			request.getRequestDispatcher("publicationEtMessages.jsp").forward(request, response);
		}
		if(operation.equals("commenterPublication")) {
			System.out.println("\n\n\n on est la\n\n");
			int idPublication = Integer.parseInt(request.getParameter("cible"));
			String commentaireTxt = request.getParameter("commentaire");
			String usernameActif = (String) request.getSession().getAttribute("usernameActif");
			facade.ajouterCommentairePublication(idPublication,usernameActif, commentaireTxt);
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		if (operation.equals("afficherDecks")){
			System.out.println("\n\n On veut afficher les decks \n\n");
			request.setAttribute("liste_d", facade.getListeDeck((String) request.getSession().getAttribute("usernameActif")));
			request.getRequestDispatcher("afficherDeck.jsp").forward(request, response);
		}
		if (operation.equals("VoirDeck")){
			System.out.println("\n\n On veut voir un deck \n\n");

			Collection<Deck> ds =facade.getListeDeck((String) request.getSession().getAttribute("usernameActif"));
			Deck AVoir = null;
			int IdATrouver = Integer.parseInt(request.getParameter("deckId"));
			for (Deck d:ds) {
				if (d.getId()==IdATrouver) {
					AVoir = d;
					request.getSession().setAttribute("idDeckActif", d.getId());
				}
			}
			request.setAttribute("deck", AVoir);
			request.getRequestDispatcher("VoirDeck.jsp").forward(request, response);
		}
		if (operation.equals("creationDeck")){
			System.out.println("\n\n On vas dans l'onglet creationDeck \n\n");
			//System.out.println(facade.getListeCartes());
			request.getRequestDispatcher("creationDeck.jsp").forward(request, response);
		}
		if (operation.equals("creerDeck")){
			System.out.println("\n\n On veut créer un deck \n\n");
			String username = (String) request.getSession().getAttribute("usernameActif");
			String nom = (String) request.getParameter("nomDeck");
			facade.ajouterDeck(username,nom);
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		if (operation.equals("ajouterADeck")){
			System.out.println("\n\n On veut ajouter des cartes dans un deck \n\n");
			String username = (String) request.getSession().getAttribute("usernameActif");
			request.setAttribute("liste_c", facade.getCompte(username).getCartes());
			request.getRequestDispatcher("ajouterCarteDeck.jsp").forward(request, response);
		}
		if(operation.equals("ajouterLaCarte")){
			System.out.println("\n\n On veut ajouter la carte dans un deck \n\n");
			int idCarte = Integer.parseInt(request.getParameter("carteId"));
			int idDeck = (int) request.getSession().getAttribute("idDeckActif");
			//String username = (String) request.getSession().getAttribute("usernameActif");
			System.out.println("\n\n yes \n\n");
			facade.ajouterCarteADeck(idCarte, idDeck);
			System.out.println("\n\n non \n\n");
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		
	}

}
