package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListOwners;

/**
 * Servlet implementation class EditOwnerServlet
 */
@WebServlet("/editOwnerServlet")
public class EditOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOwnerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		ListOwnersHelper loh = new ListOwnersHelper();
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		ListOwners ownerToUpdate = loh.searchForOwnerById(tempId);
		String ownerEntered = request.getParameter("ownerName");
		// set fields to newly entered strings
		ownerToUpdate.setOwnerName(ownerEntered);
		// update the entry with matching ID to match this object
		loh.updateOwner(ownerToUpdate);

		getServletContext().getRequestDispatcher("/viewAllAuthorsServlet").forward(request, response);
	}

}
