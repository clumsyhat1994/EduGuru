package eduguru.servlet;

import eduguru.dal.*;
import eduguru.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there
 * is a difference: doGet() handles the http GET request. This method is called
 * when you put in the /findusers URL in the browser. doPost() handles the http
 * POST request. This method is called after you click the submit button.
 * 
 * To run: 1. Run the SQL script to recreate your database schema:
 * http://goo.gl/86a11H. 2. Insert test data. You can do this by running
 * blog.tools.Inserter (right click, Run As > JavaApplication. Notice that this
 * is similar to Runner.java in our JDBC example. 3. Run the Tomcat server at
 * localhost. 4. Point your browser to
 * http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/studentLogin")
public class StudentLogin extends HttpServlet {

	protected StudentsDao studentsDao;

	@Override
	public void init() throws ServletException {
		// usersDao = UsersDao.getInstance();
		studentsDao = StudentsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		req.getRequestDispatcher("/StudentLogin.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		Students student = null;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null || username.trim().isEmpty()) {
			messages.put("title", "Please enter a valid username.");
			messages.put("disableSubmit", "true");
		} else if (password == null || password.trim().isEmpty()) {
			messages.put("title", "Please enter a valid password.");
			messages.put("disableSubmit", "true");
		} else {
			// Retrieve Users, and store as a message.
			try {
				student = studentsDao.getStudentsByUsernamePassword(username,
						password);
				if (student == null) {
					messages.put("title", "invalid username or password");
					messages.put("disableSubmit", "true");
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("student", student);
					Cookie usernameCookie = new Cookie("username", username);
					resp.addCookie(usernameCookie);
					messages.put("success", "Welcome, " + username);

				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		String encodedURL = resp.encodeRedirectUrl("LoginSuccess.jsp");
		resp.sendRedirect(encodedURL);
		// req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
	}
}
