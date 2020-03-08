package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListAnimals;
import model.ListOwners;

/**
 * Servlet implementation class EditAnimalServlet
 */
@WebServlet("/editAnimalServlet")
public class EditAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAnimalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		ListAnimalsHelper lah = new ListAnimalsHelper();
		String updatedName = request.getParameter("name");
		String updatedType = request.getParameter("type");
		Integer tempId = Integer.parseInt(request.getParameter("id"));

		ListAnimals animalToUpdate = lah.searchForAnimalById(tempId);

		ListOwnersHelper loh = new ListOwnersHelper();
		String ownerEntered = request.getParameter("owner");
		List<ListOwners> matchOwners = loh.searchForOwnerByName(ownerEntered);
		if (matchOwners.isEmpty()) {
			// if no match, add new owner to the database then get that entry from the
			// database so we know the id.
			ListOwners selectedOwner = new ListOwners();
			selectedOwner.setOwnerName(ownerEntered);
			loh.insertOwner(selectedOwner);
			matchOwners = loh.searchForOwnerByName(ownerEntered);
		}
		ListOwners updatedOwner = matchOwners.get(0);
		animalToUpdate.setName(updatedName);
		animalToUpdate.setOwner(updatedOwner);
		animalToUpdate.setType(updatedType);

		lah.updateAnimal(animalToUpdate);

		getServletContext().getRequestDispatcher("/viewAllAnimalsServlet").forward(request, response);
	}

}
