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
import eduguru.model.Courses.CourseCategory;

/**
 * Servlet implementation class FindCategory
 */
@WebServlet("/FindCategory")
public class FindCategory extends HttpServlet {

	protected CoursesDao coursesDao;

	@Override
	public void init() throws ServletException {
		coursesDao = CoursesDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<CourseCategory> courseCategories = null;

		try {
			courseCategories = coursesDao.getOrderedCourseCategories();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Top course categories " + courseCategories);

		req.setAttribute("courseCategories", courseCategories);

		req.getRequestDispatcher("/FindCategories.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<CourseCategory> courseCategories = null;

		try {
			courseCategories = coursesDao.getOrderedCourseCategories();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Top course categories " + courseCategories);

		req.setAttribute("courseCategories", courseCategories);

		req.getRequestDispatcher("/FindCategories.jsp").forward(req, resp);
	}

}
