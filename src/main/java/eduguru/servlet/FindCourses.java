package eduguru.servlet;

import eduguru.dal.*;
import eduguru.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findcourses")
public class FindCourses extends HttpServlet {

	protected CoursesDao coursesDao;

	@Override
	public void init() throws ServletException {
		coursesDao = CoursesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Courses> courses = null;
		String category = req.getParameter("category");
		if (category == null || category.trim().isEmpty()) {
			messages.put("success", "Please enter a valid category.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				courses = coursesDao.getCourseByCategory(category);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + category);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("courseCategory", category);
		}
		req.setAttribute("courses", courses);

		req.getRequestDispatcher("/FindCourses.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Courses> courses = null;
		String category = req.getParameter("category");
		if (category == null || category.trim().isEmpty()) {
			messages.put("success", "Please enter a valid category.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				courses = coursesDao.getCourseByCategory(category);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + category);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("courseCategory", category);
		}
		req.setAttribute("courses", courses);

		req.getRequestDispatcher("/FindCourses.jsp").forward(req, resp);
	}
}
