package eduguru.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduguru.model.Instructors;
import eduguru.model.Users;

public class InstructorsDao extends UsersDao {

	private static InstructorsDao instance = null;

	protected InstructorsDao() {
		super();
	}

	public static InstructorsDao getInstance() {
		if (instance == null) {
			instance = new InstructorsDao();
		}
		return instance;
	}

	public Instructors create(Instructors instructor) throws SQLException {
		create(new Users(instructor.getUsername(), instructor.getPassword(),
				instructor.getEmail()));

		String insertInstructor = "INSERT INTO Instructors(username, fullName) VALUES(?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertInstructor);
			insertStmt.setString(1, instructor.getUsername());
			insertStmt.setString(2, instructor.getFullName());
			insertStmt.executeUpdate();

			return instructor;
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
		}
	}

	public Instructors getInstructorsByUsername(String username)
			throws SQLException {
		String selectInstructor = "SELECT * "
				+ "FROM Instructors INNER JOIN Users " + "  using(username) "
				+ "WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInstructor);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUsername = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				String fullNameString = results.getString("fullName");
				Instructors instructor = new Instructors(resultUsername,
						password, email, fullNameString);
				return instructor;
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
		return null;
	}

	public Instructors delete(Instructors instructor) throws SQLException {
		String deleteInstructor = "DELETE FROM Instructors WHERE username=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteInstructor);
			deleteStmt.setString(1, instructor.getUsername());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"No records available to delete for UserName="
								+ instructor.getUsername());
			}
			super.delete(instructor);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public List<Instructors> findTopTenInstructorsByCategory(
			String courseCategory) throws SQLException {
		List<Instructors> instructors = new ArrayList<>();
		String selectInstructors = "SELECT "
				+ "InstructorCatalog.username, fullname, AVG(commentRating) AS avgRating "
				+ "FROM (SELECT * FROM Courses WHERE "
				+ "Courses.courseCategory =?) AS CategoryTable INNER JOIN "
				+ "Comments USING (courseId) INNER JOIN "
				+ "InstructorCatalog USING (courseId) INNER JOIN "
				+ "instructors ON instructors.username = InstructorCatalog.username "
				+ "GROUP BY InstructorCatalog.username "
				+ "ORDER BY avgRating DESC LIMIT 20;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectInstructors);
			selectStmt.setString(1, courseCategory);
			results = selectStmt.executeQuery();
			Instructors instructor = null;
			InstructorsDao instructorsDao = InstructorsDao.getInstance();
			while (results.next()) {

				String instructorUsername = results.getString("username");
				instructor = instructorsDao
						.getInstructorsByUsername(instructorUsername);

				instructors.add(instructor);
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
		return instructors;

	}

}
