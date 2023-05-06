package eduguru.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduguru.model.Comments;
import eduguru.model.Courses;
import eduguru.model.Students;

public class CommentsDao {

	protected ConnectionManager connectionManager;

	private static CommentsDao instance = null;

	protected CommentsDao() {
		connectionManager = new ConnectionManager();
	}

	public static CommentsDao getInstance() {
		if (instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}

	public Comments getCommentById(int commentId) throws SQLException {
		String selectComment = "SELECT * " + "FROM Comments "
				+ "WHERE commentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			StudentsDao studentsDao = StudentsDao.getInstance();
			CoursesDao coursesDao = CoursesDao.getInstance();

			if (results.next()) {
				int resultCommentId = results.getInt("commentId");
				String usernameString = results.getString("username");
				Students student = studentsDao
						.getStudentsByUsername(usernameString);
				String displayNameString = results
						.getString("displayNameComments");
				String commentText = results.getString("commentText");
				int courseId = results.getInt("courseId");
				Courses course = coursesDao.getCourseById(courseId);
				double commentRating = results.getDouble("commentRating");
				Date commentDate = results.getDate("commentDate");
				Comments comment = new Comments(resultCommentId, student,
						course, displayNameString, commentText, commentRating,
						commentDate);
				return comment;
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
