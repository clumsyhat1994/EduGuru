package eduguru.servlet;

import eduguru.dal.*;
import eduguru.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findsubscribes")
public class FindSubscribes extends HttpServlet {

	protected SubscribesDao subscribesDao;

	@Override
	public void init() throws ServletException {
		subscribesDao = SubscribesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate UserName.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("title", "Invalid username.");
		} else {
			messages.put("title", "Subscribed courses");
		}

		// Retrieve BlogUsers, and store in the request.
		List<Subscribes> subscribes = new ArrayList<>();
		try {
			Students student = new Students(userName);
			subscribes = subscribesDao.getSubscribesByUsername(student);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		req.setAttribute("subscribes", subscribes);
		req.getRequestDispatcher("/FindSubscribes.jsp").forward(req, resp);
	}

}
