package eduguru.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduguru.model.Students;
import eduguru.model.Users;

public class StudentsDao extends UsersDao {

	private static StudentsDao instance = null;

	protected StudentsDao() {
		super();
	}

	public static StudentsDao getInstance() {
		if (instance == null) {
			instance = new StudentsDao();
		}
		return instance;
	}

	public Students create(Students student) throws SQLException {
		create(new Users(student.getUsername(), student.getPassword(),
				student.getEmail()));

		String insertStudent = "INSERT INTO Students(username, cutBudget, displayName) VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertStudent);
			insertStmt.setString(1, student.getUsername());
			insertStmt.setBoolean(2, student.getCutBudget());
			insertStmt.setString(3, student.getDisplayName());
			insertStmt.executeUpdate();
			
			return student;
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
	
	public Students delete(Students student) throws SQLException {
		String deleteStudent = "DELETE FROM Students WHERE username=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteStudent);
			deleteStmt.setString(1, student.getUsername());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + student.getUsername());
			}
			super.delete(student);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public Students updateEmail(Students student, String newEmail) throws SQLException {
		super.updateEmail(student, newEmail);
		return student;
	}

	public Students getStudentsByUsername(String username) throws SQLException {
		String selectStudent = "SELECT * " + "FROM Students INNER JOIN Users "
				+ "  using(username) " + "WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudent);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUsername = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Boolean cutBudget = results.getBoolean("cutBudget");
				String displayNameString = results.getString("displayName");
				Students student = new Students(resultUsername, password, email,
						cutBudget, displayNameString);
				return student;
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

	public Students getStudentsByUsernamePassword(String username,
			String password) throws SQLException {
		String selectStudent = "SELECT * " + "FROM Students INNER JOIN Users "
				+ "  using(username) " + "WHERE username=? && password=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStudent);
			selectStmt.setString(1, username);
			selectStmt.setString(2, password);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUsername = results.getString("username");
				String resultPassword = results.getString("password");
				String email = results.getString("email");
				Boolean cutBudget = results.getBoolean("cutBudget");
				String displayNameString = results.getString("displayName");
				Students student = new Students(resultUsername, resultPassword,
						email, cutBudget, displayNameString);
				return student;
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
}