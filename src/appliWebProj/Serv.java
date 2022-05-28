package appliWebProj;

import java.io.File;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		if(operation.equals("creerCarte")) {
			String nomCarte = request.getParameter("nomCarte");
			Part imagePart = request.getPart("imageCarte");
		    String nomImageCarte = imagePart.getSubmittedFileName();
		    File imagesDir = new File(System.getProperty("jboss.server.data.dir"), "imagesCarte");
		    imagesDir.mkdir();
		    for (Part part : request.getParts()) {
		      part.write(imagesDir+ "/" +nomImageCarte);
		    }
		    response.getWriter().print("The file uploaded sucessfully.");
			System.out.println("\n\n");
		    System.out.println(imagesDir + "/" + nomImageCarte);

			facade.creerCarte(nomCarte,imagesDir + "/" + nomImageCarte);
			request.getRequestDispatcher("index.html").forward(request, response);	
		}
		if(operation.equals("listerCartes")) {
			//System.out.println(facade.getListeCartes());
			request.setAttribute("liste_c", facade.getListeCartes());
			request.getRequestDispatcher("afficherCarte.jsp").forward(request, response);
		}
		if(operation.equals("acheterDeck")) {
			request.setAttribute("cartesObtenues", facade.getPlusieursCartes(nbCartesPaquet));
			request.getRequestDispatcher("ouverturePaquetCartes.jsp").forward(request, response);
		}
		if(operation.equals("afficherPublication")) {
			request.setAttribute("listePublicat", facade.getPublications());
			request.getRequestDispatcher("afficherListePublication.jsp").forward(request, response);
		}
	}

}
