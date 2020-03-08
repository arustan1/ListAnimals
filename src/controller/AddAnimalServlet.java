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
 * Servlet implementation class AddAnimalServlet
 */
@WebServlet("/addAnimalServlet")
public class AddAnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnimalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String owner = request.getParameter("owner");
		
		System.out.println("Name: " + name + " Type: " + type + " Owner: " + owner);
		ListOwnersHelper loh = new ListOwnersHelper();
		List<ListOwners> matchOwners = loh.searchForOwnerByName(owner);
		
		// if no match, add new owner to the database then get that entry from the
		// database so we know the id.
		if (matchOwners.isEmpty()) {
			ListOwners selectedOwner = new ListOwners();
			selectedOwner.setOwnerName(owner);
			loh.insertOwner(selectedOwner);
			matchOwners = loh.searchForOwnerByName(owner);
		}
		ListOwners updatedOwner = matchOwners.get(0);

		ListAnimals la = new ListAnimals(name, type, updatedOwner);
		ListAnimalsHelper lah = new ListAnimalsHelper();
		lah.insertAnimal(la);

		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
