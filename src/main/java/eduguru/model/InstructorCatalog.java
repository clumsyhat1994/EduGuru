package eduguru.model;

public class InstructorCatalog {
	protected int instructorCatalogId;
	protected Instructors instructor;
	protected Courses course;

	public InstructorCatalog(int instructorCatalogId, Instructors instructor,
			Courses course) {
		this.instructorCatalogId = instructorCatalogId;
		this.instructor = instructor;
		this.course = course;
	}

	public InstructorCatalog(Instructors instructor, Courses course) {
		this.instructor = instructor;
		this.course = course;
	}

	public int getInstructorCatalogId() {
		return instructorCatalogId;
	}

	public void setInstructorCatalogId(int instructorCatalogId) {
		this.instructorCatalogId = instructorCatalogId;
	}

	public Instructors getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructors instructor) {
		this.instructor = instructor;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}
}
