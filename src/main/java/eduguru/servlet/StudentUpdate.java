package eduguru.servlet;

import eduguru.dal.*;
import eduguru.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentupdate")
public class StudentUpdate extends HttpServlet {

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

		// Retrieve user and validate.
		String username = req.getParameter("username");
		if (username == null || username.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserName.");
		} else {
			try {
				Students student = studentsDao.getStudentsByUsername(username);
				if (student == null) {
					messages.put("success", "UserName does not exist.");
				}
				req.setAttribute("student", student);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/StudentUpdate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve user and validate.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Please enter a valid UserName.");
		} else {
			try {
				
				Students student = studentsDao.getStudentsByUsername(userName);
				if (student == null) {
					messages.put("success",
							"UserName does not exist. No update to perform.");
				} else {
					String newEmail = req.getParameter("email");
					if (newEmail == null || newEmail.trim().isEmpty()) {
						messages.put("success",
								"Please enter a valid LastName.");
					} else {
						student = studentsDao.updateEmail(student, newEmail);
						messages.put("success",
								"Successfully updated " + userName);
					}
				}
				req.setAttribute("student", student);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/StudentUpdate.jsp").forward(req, resp);
	}
}
