package eduguru.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import eduguru.model.Courses;
import eduguru.model.Courses.CourseCategory;
import eduguru.model.Instructors;

public class CoursesDao {

	protected ConnectionManager connectionManager;

	private static CoursesDao instance = null;

	protected CoursesDao() {
		connectionManager = new ConnectionManager();
	}

	public static CoursesDao getInstance() {
		if (instance == null) {
			instance = new CoursesDao();
		}
		return instance;
	}

	public Courses getCourseById(int courseId) throws SQLException {
		String selectCourse = "SELECT * " + "FROM Courses "
				+ "WHERE courseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCourse);
			selectStmt.setInt(1, courseId);
			results = selectStmt.executeQuery();

			if (results.next()) {

				int resultCourseId = results.getInt("courseId");
				String titleString = results.getString("title");
				Boolean isPaid = results.getBoolean("isPaid");
				double price = results.getDouble("price");
				String headline = results.getString("headline");
				double rating = results.getDouble("avgRating");
				int numLectures = results.getInt("numLectures");
				double contentLengthMin = results.getDouble("contentLengthMin");
				Date publishedTimeDate = results.getDate("publishedTime");
				Date lastUpdateDate = results.getDate("lastUpdateDate");
				String courseCategory = results.getString("courseCategory");
				String languageString = results.getString("language");

				Courses resultCourse = new Courses(resultCourseId, titleString,
						isPaid, price, headline, rating, numLectures,
						contentLengthMin, publishedTimeDate, lastUpdateDate,
						CourseCategory.fromString(courseCategory),
						languageString);
				return resultCourse;
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

	/**
	 * retrieve top 10 subscribed courses taught in English under a given
	 * category.
	 * 
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public List<Courses> getCourseByCategory(String category)
			throws SQLException {
		List<Courses> courses = new ArrayList<>();
		String selectCourse = "SELECT * FROM Courses "
				+ " WHERE courseCategory=? && language=\"english\" ORDER BY avgRating DESC LIMIT 50;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCourse);
			selectStmt.setString(1, category);
			results = selectStmt.executeQuery();
			while (results.next()) {

				int resultCourseId = results.getInt("courseId");
				String titleString = results.getString("title");
				Boolean isPaid = results.getBoolean("isPaid");
				double price = results.getDouble("price");
				String headline = results.getString("headline");
				double rating = results.getDouble("avgRating");
				int numLectures = results.getInt("numLectures");
				double contentLengthMin = results.getDouble("contentLengthMin");
				Date publishedTimeDate = results.getDate("publishedTime");
				Date lastUpdateDate = results.getDate("lastUpdateDate");
				String courseCategory = results.getString("courseCategory");
				String languageString = results.getString("language");

				Courses resultCourse = new Courses(resultCourseId, titleString,
						isPaid, price, headline, rating, numLectures,
						contentLengthMin, publishedTimeDate, lastUpdateDate,
						CourseCategory.fromString(courseCategory),
						languageString);
				courses.add(resultCourse);
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
		return courses;
	}

	public List<CourseCategory> getOrderedCourseCategories()
			throws SQLException {
		List<CourseCategory> courseCategories = new ArrayList<>();
		String selectCourseCategory = "SELECT Courses.courseCategory, COUNT(title) "
				+ " AS numCourses FROM Courses GROUP BY courseCategory "
				+ "ORDER BY numCourses DESC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCourseCategory);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String courseCategory = results.getString("courseCategory");
				CourseCategory resultCourseCategory = CourseCategory
						.fromString(courseCategory);
				courseCategories.add(resultCourseCategory);
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
		return courseCategories;
	}

	public List<Courses> getCoursesByInstructor(Instructors instructor)
			throws SQLException {
		List<Courses> courses = new ArrayList<>();
		String selectCourses = "SELECT * FROM " + "    (SELECT * FROM "
				+ "instructors WHERE " + "username =?) AS instructors "
				+ "INNER JOIN " + "instructorcatalog USING (username) "
				+ "INNER JOIN courses USING (courseId) "
				+ "ORDER BY avgRating DESC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCourses);
			selectStmt.setString(1, instructor.getUsername());
			results = selectStmt.executeQuery();
			while (results.next()) {

				int resultCourseId = results.getInt("courseId");
				String titleString = results.getString("title");
				Boolean isPaid = results.getBoolean("isPaid");
				double price = results.getDouble("price");
				String headline = results.getString("headline");
				double rating = results.getDouble("avgRating");
				int numLectures = results.getInt("numLectures");
				double contentLengthMin = results.getDouble("contentLengthMin");
				Date publishedTimeDate = results.getDate("publishedTime");
				Date lastUpdateDate = results.getDate("lastUpdateDate");
				String courseCategory = results.getString("courseCategory");
				String languageString = results.getString("language");

				Courses resultCourse = new Courses(resultCourseId, titleString,
						isPaid, price, headline, rating, numLectures,
						contentLengthMin, publishedTimeDate, lastUpdateDate,
						CourseCategory.fromString(courseCategory),
						languageString);

				courses.add(resultCourse);
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
		return courses;
	}
}
