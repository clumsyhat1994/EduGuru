package eduguru.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduguru.model.Users;

public class UsersDao {

	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;

	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}

	public static UsersDao getInstance() {
		if (instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		String insertPerson = "INSERT INTO Users(username,password,email) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getEmail());
			insertStmt.executeUpdate();

			return user;
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

	/**
	 * Update the LastName of the Persons instance. This runs a UPDATE
	 * statement.
	 */
	public Users updateEmail(Users user, String newEmail) throws SQLException {
		String updatePerson = "UPDATE Users SET Email=? WHERE username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newEmail);
			updateStmt.setString(2, user.getUsername());
			updateStmt.executeUpdate();

			user.setEmail(newEmail);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Persons instance. This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE username=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUsername());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons
			// instance.
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

	/**
	 * Get the Persons record by fetching it from your MySQL instance. This runs
	 * a SELECT statement and returns a single Persons instance.
	 */
	public Users getUserByUsername(String username) throws SQLException {
		String selectUser = "SELECT username, password, email FROM Users WHERE username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, username);
			// Note that we call executeQuery(). This is used for a SELECT
			// statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only
			// retrieves
			// the first record). The cursor is initially positioned before the
			// row.
			// Furthermore, you can retrieve fields by name and by type.
			if (results.next()) {
				String resultUsername = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				Users user = new Users(resultUsername, password, email);
				return user;
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
