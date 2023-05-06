package eduguru.servlet;

import eduguru.dal.*;
import eduguru.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentcreate")
public class StudentCreate extends HttpServlet {

	protected StudentsDao studentsDao;

	@Override
	public void init() throws ServletException {
		studentsDao = StudentsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		req.getRequestDispatcher("/StudentCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate name and password.
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String cutBudget = req.getParameter("cutBudget");
		String displayNameString = req.getParameter("displayName");

		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else if (password == null || password.trim().isEmpty()) {
			messages.put("success", "Invalid Password");
		} else if (email == null || email.trim().isEmpty()) {
			messages.put("success", "Invalid Email");
		} else {
			// Create the Student.
			try {
				// Exercise: parse the input for StatusLevel.
				Students student = new Students(userName, password, email,
						Boolean.valueOf(cutBudget), displayNameString);
				student = studentsDao.create(student);
				messages.put("success", "Successfully created " + userName);
			} catch (SQLException e) {
				if (e instanceof SQLIntegrityConstraintViolationException) {
					messages.put("success", userName + " already exists.");
				} else {
					e.printStackTrace();
					throw new IOException(e);
				}
			}
		}
		req.getRequestDispatcher("/StudentCreate.jsp").forward(req, resp);
	}
}
