package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ListOwners;

/**
 * Servlet implementation class OwnerNavigationServlet
 */
@WebServlet("/ownerNavigationServlet")
public class OwnerNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OwnerNavigationServlet() {
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

		ListOwnersHelper loh = new ListOwnersHelper();

		String act = request.getParameter("doThisToItem");
		// after all changes, we should redirect to the viewAllOwners servlet
		// The only time we don't is if they select to add a new item or edit
		String path = "/viewAllOwnersServlet";
		boolean test = false;

		if (act.equals("Delete")) {
			try {
				if (test) {
					System.out.println("start delete");
				}
				Integer tempOwnerId = Integer.parseInt(request.getParameter("id"));
				ListOwners ownerToDelete = loh.searchForOwnerById(tempOwnerId);
				loh.deleteOwner(ownerToDelete);

			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an owner");
			}

		} else if (act.equals("Edit")) {
			try {
				if (test) {
					System.out.println("start edit");
				}

				Integer tempID = Integer.parseInt(request.getParameter("id"));
				ListOwners ownerToEdit = loh.searchForOwnerById(tempID);
				request.setAttribute("ownerToEdit", ownerToEdit);
				path = "/edit-owner.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an owner");
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
