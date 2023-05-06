package eduguru.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eduguru.model.Courses;
import eduguru.model.Students;
import eduguru.model.Subscribes;

public class SubscribesDao {

	protected ConnectionManager connectionManager;

	private static SubscribesDao instance = null;

	protected SubscribesDao() {
		connectionManager = new ConnectionManager();
	}

	public static SubscribesDao getInstance() {
		if (instance == null) {
			instance = new SubscribesDao();
		}
		return instance;
	}

	public Subscribes create(Subscribes subscribe) throws SQLException {
		String insertReshare = "INSERT INTO Subscribes(username, courseId) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReshare,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, subscribe.getStudents().getUsername());
			insertStmt.setInt(2, subscribe.getCourses().getCourseId());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by
			// the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int subscribeId = -1;
			if (resultKey.next()) {
				subscribeId = resultKey.getInt(1);
			} else {
				throw new SQLException(
						"Unable to retrieve auto-generated key.");
			}
			subscribe.setSubscribeId(subscribeId);
			return subscribe;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public List<Subscribes> getSubscribesByUsername(Students student)
			throws SQLException {
		List<Subscribes> subscribes = new ArrayList<Subscribes>();
		String selectReshares = "SELECT * FROM "
				+ "Subscribes INNER JOIN Courses using(courseId) WHERE username =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReshares);
			selectStmt.setString(1, student.getUsername());
			results = selectStmt.executeQuery();
			CoursesDao coursesDao = CoursesDao.getInstance();
			StudentsDao studentsDao = StudentsDao.getInstance();

			while (results.next()) {
				int subscribeId = results.getInt("subscribeId");
				int courseId = results.getInt("courseId");
				String usernameString = results.getString("username");
				Students resultStudent = studentsDao
						.getStudentsByUsername(usernameString);
				Courses course = coursesDao.getCourseById(courseId);
				Subscribes resultSubscribe = new Subscribes(subscribeId,
						resultStudent, course);
				subscribes.add(resultSubscribe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return subscribes;
	}
}
