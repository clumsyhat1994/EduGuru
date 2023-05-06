package eduguru.tools;

import java.sql.SQLException;
import java.util.List;

import eduguru.dal.*;
import eduguru.model.*;
import eduguru.model.Courses.CourseCategory;

/**
 * main() runner, used for the app demo.
 * 
 * Instructions: 1. Create a new MySQL schema and then run the CREATE TABLE
 * statements from lecture: http://goo.gl/86a11H. 2. Update ConnectionManager
 * with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		StudentsDao studentsDao = StudentsDao.getInstance();
		CoursesDao coursesDao = CoursesDao.getInstance();
		SubscribesDao subscribesDao = SubscribesDao.getInstance();
		InstructorsDao instructorsDao = InstructorsDao.getInstance();
		CommentsDao commentsDao = CommentsDao.getInstance();

		// CREATE

		// test create User
		Users user1 = new Users("a", "123", "a.gmail.com");
		user1 = usersDao.create(user1);
		printResult("created user a", user1);
		
		// test create student
		Students student1 = new Students("bob", "333", "email", true, "Bob King");
		student1 = studentsDao.create(student1);
		printResult("create student bob", student1);
		
		// test create instructor
		Instructors instructor1 = new Instructors("wonderfulteacher", "789", "email", "rc");
		instructor1 = instructorsDao.create(instructor1);
		printResult("create instructor wonderfulteacher", instructor1);

		// READ

		// test getUserByUserName
		Users userResult = usersDao.getUserByUsername("a");
		printResult("getUserByUserName", userResult);
		
		// test getStudentByUsernamePassword
	    Students student = studentsDao.getStudentsByUsernamePassword("bob", "333");
	    printResult("getStudentByUsernamePassword", student);
	    
	    // test getInstructorsByUsername
	    Instructors instructor = instructorsDao.getInstructorsByUsername("wonderfulteacher");
	    printResult("getInstructorsByUsername", instructor);
		
		// test getCourseById
		Courses resultCourse = coursesDao.getCourseById(58377);
		printResult("getCourseById", resultCourse);
		
		// test getSubscribesByUsername
		List<Subscribes> subscribes = subscribesDao.getSubscribesByUsername(studentsDao.getStudentsByUsername("aabbett49"));
		printResult("getSubscribesByUsername", subscribes);
		
		// test getCourseByCategory
		List<Courses> courses = coursesDao.getCourseByCategory("It & Software");
		printResult("getCourseByCategory", courses);
		
		// test getOrderedCourseCategories
		List<CourseCategory> courseCategories = coursesDao.getOrderedCourseCategories();
		printResult("getOrderedCourseCategories", courseCategories);
		
		// test getCommentById
		Comments comment = commentsDao.getCommentById(1);
		printResult("getCommentById", comment);
		
		// test findTopTenInstructorsByCategory
		List<Instructors> instructors = instructorsDao.findTopTenInstructorsByCategory("Development");
		System.out.println("findTopTenInstructorsByCategory Development");
		for (Instructors i : instructors) {
			System.out.print(i.getUsername() + " " + i.getFullName());
			System.out.println(" ");
		}
		
		// test getCoursesByInstructor
		Instructors instructor2 = instructorsDao.getInstructorsByUsername("armin-kerscher");
		List<Courses> courses2 = coursesDao.getCoursesByInstructor(instructor2);
		printResult("getCoursesByInstructor", courses2);
		
		// UPDATE

		// test updateEmail
		user1 = usersDao.updateEmail(user1, "a.xmail.com");
		printResult("updated user a", user1);
		
		// test updateEmail for student
		student1 = studentsDao.updateEmail(student1, "newEmail");
		printResult("update student bob email", student1.getEmail());

		// DELETE

		// test delete User
		user1 = usersDao.delete(user1);
		printResult("deleted user a", user1);
		
		// test delete Student
		student1 = studentsDao.delete(student1);
		printResult("deleted student bob", student1);
		
		// test delete Instructor
		instructor1 = instructorsDao.delete(instructor1);
		printResult("deleted instructor wonderfulteacher", instructor1);

	}

	private static void printResult(String resultString, Object o) {
		if (o == null) {
			System.out.println(resultString + ": " + "null");
			return;
		}
		System.out.println(resultString + ": " + o.toString());
	}
}
