package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ListAnimals;

/**
 * Servlet implementation class AnimalsNavigationServlet
 */
@WebServlet("/animalsNavigationServlet")
public class AnimalsNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnimalsNavigationServlet() {
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

		String act = request.getParameter("doThisToItem");

		String path = "/viewAllAnimalsServlet";
		boolean test = true;

		if (act.equals("Delete")) {
			try {
				if (test) {
					System.out.println("start delete");
				}
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				ListAnimals animalToDelete = lah.searchForAnimalById(tempId);
				lah.deleteAnimal(animalToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an animal");
			}

		} else if (act.equals("Edit")) {
			try {
				if (test) {
					System.out.println("start edit");
				}

				Integer tempID = Integer.parseInt(request.getParameter("id"));
				ListAnimals animalToEdit = lah.searchForAnimalById(tempID);
				request.setAttribute("animalToEdit", animalToEdit);
				path = "/edit-animal.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an animal");
			}

		} else if (act.equals("Add")) {
			if (test) {
				System.out.println("start add");
			}
			path = "/index.html";
		}
		if (test) {
			System.out.println("path = " + path);
		}

		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
