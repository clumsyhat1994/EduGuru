package eduguru.model;

import eduguru.model.Courses.CourseCategory;

public class StudentInterests {
	protected int interestId;
	protected Students student;
	protected CourseCategory courseCategory;

	public StudentInterests(int interestId, Students student,
			CourseCategory courseCategory) {
		this.interestId = interestId;
		this.student = student;
		this.courseCategory = courseCategory;
	}

	public StudentInterests(Students student, CourseCategory courseCategory) {
		this.student = student;
		this.courseCategory = courseCategory;
	}

	public int getInterestId() {
		return interestId;
	}

	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}
}
