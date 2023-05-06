package eduguru.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eduguru.dal.CoursesDao;
import eduguru.dal.InstructorsDao;
import eduguru.model.Courses;
import eduguru.model.Instructors;

/**
 * Servlet implementation class FindCoursesByInstructor
 */
@WebServlet("/FindCoursesByInstructor")
public class FindCoursesByInstructor extends HttpServlet {

	protected CoursesDao coursesDao;
	protected InstructorsDao instructorsDao;

	@Override
	public void init() throws ServletException {
		coursesDao = CoursesDao.getInstance();
		instructorsDao = InstructorsDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Courses> courses = null;
		Instructors instructor = null;
		String instructorUsername = req.getParameter("instructor");
		if (instructorUsername == null || instructorUsername.trim().isEmpty()) {
			messages.put("success", "Please enter a valid category.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				// instructor = new Instructors(instructorUsername);
				instructor = instructorsDao
						.getInstructorsByUsername(instructorUsername);
				courses = coursesDao.getCoursesByInstructor(instructor);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success",
					"Displaying results for " + instructorUsername);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("instructorUsername ", instructorUsername);
		}
		req.setAttribute("courses", courses);
		req.setAttribute("instructor", instructor);

		req.getRequestDispatcher("/FindCoursesByInstructor.jsp").forward(req,
				resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Courses> courses = null;
		Instructors instructor = null;
		String instructorUsername = req.getParameter("instructor");
		if (instructorUsername == null || instructorUsername.trim().isEmpty()) {
			messages.put("success", "Please enter a valid category.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				// instructor = new Instructors(instructorUsername);
				instructor = instructorsDao
						.getInstructorsByUsername(instructorUsername);
				courses = coursesDao.getCoursesByInstructor(instructor);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success",
					"Displaying results for " + instructorUsername);
			// Save the previous search term, so it can be used as the default
			// in the input box when rendering FindUsers.jsp.
			messages.put("instructorUsername ", instructorUsername);
		}
		req.setAttribute("courses", courses);
		req.setAttribute("instructor", instructor);

		req.getRequestDispatcher("/FindCoursesByInstructor.jsp").forward(req,
				resp);

	}
}
