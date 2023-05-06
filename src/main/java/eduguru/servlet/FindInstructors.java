package eduguru.servlet;

import eduguru.dal.*;
import eduguru.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class FindInstructors
 */
@WebServlet("/FindInstructors")
public class FindInstructors extends HttpServlet {
	protected InstructorsDao instructorsDao;

	@Override
	public void init() throws ServletException {
		instructorsDao = InstructorsDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Instructors> instructors = null;
		String category = req.getParameter("category");
		if (category == null || category.trim().isEmpty()) {
			messages.put("success", "Please enter a valid category.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				instructors = instructorsDao
						.findTopTenInstructorsByCategory(category);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + category);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("courseCategory", category);
		}
		req.setAttribute("instructors", instructors);

		req.getRequestDispatcher("/FindInstructors.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Instructors> instructors = null;
		String category = req.getParameter("category");
		if (category == null || category.trim().isEmpty()) {
			messages.put("success", "Please enter a valid category.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				instructors = instructorsDao
						.findTopTenInstructorsByCategory(category);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + category);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("courseCategory", category);
		}
		req.setAttribute("instructors", instructors);

		req.getRequestDispatcher("/FindInstructors.jsp").forward(req, resp);
	}

}
