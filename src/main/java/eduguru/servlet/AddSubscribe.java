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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addSubscribe")
public class AddSubscribe extends HttpServlet {

	protected SubscribesDao subscribesDao;
	protected StudentsDao studentsDao;
	protected CoursesDao coursesDao;

	@Override
	public void init() throws ServletException {
		subscribesDao = SubscribesDao.getInstance();
		studentsDao = StudentsDao.getInstance();
		coursesDao = CoursesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		messages.put("title", "Subscribe a course");
		
		req.getRequestDispatcher("/AddSubscribe.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		// Retrieve and validate name.
		String userName = req.getParameter("username");
		String courseId = req.getParameter("courseId");

		Subscribes subscribe = null;
		Students student = null;
		Courses course = null;
//		if (userName == null || userName.trim().isEmpty()) {
//			messages.put("title", "Invalid UserName");
//			messages.put("disableSubmit", "true");
//		} else if (courseId == null || courseId.trim().isEmpty()) {
//			messages.put("title", "Invalid CourseId");
//			messages.put("disableSubmit", "true");
//		} else {
			try {

				student = studentsDao.getStudentsByUsername(userName);
				course = coursesDao.getCourseById(Integer.valueOf(courseId));
				subscribe = new Subscribes(student, course);
				subscribe = subscribesDao.create(subscribe);

				// Update the message.
				if (subscribe == null) {

					messages.put("title",
							"Failed to subscribe " + course.getTitle());
					messages.put("disableSubmit", "false");
				} else {

					messages.put("title",
							"Successfully subscribed " + course.getTitle());
					messages.put("disableSubmit", "true");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		//}

		req.setAttribute("student", student);
		req.setAttribute("course", course);
		req.getRequestDispatcher("/AddSubscribe.jsp").forward(req, resp);
	}
}
