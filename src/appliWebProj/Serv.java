package appliWebProj;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Serv
 */
@WebServlet("/Serv")
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB
    Facade facade; 
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
			facade.creerCarte(nomCarte);
			request.getRequestDispatcher("index.html").forward(request, response);
			
		}if(operation.equals("listerCartes")) {
			//System.out.println(facade.getListeCartes());
			request.setAttribute("liste_c", facade.getListeCartes());
			request.getRequestDispatcher("afficherCarte.jsp").forward(request, response);
		}
	}

}
