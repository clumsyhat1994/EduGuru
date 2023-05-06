package eduguru.model;

public class InstructorExpertise {
	protected int expertiseId;
	protected Instructors instructor;
	protected Courses course;

	public InstructorExpertise(int expertiseId, Instructors instructor,
			Courses course) {
		this.expertiseId = expertiseId;
		this.instructor = instructor;
		this.course = course;
	}

	public InstructorExpertise(Instructors instructor, Courses course) {
		this.instructor = instructor;
		this.course = course;
	}

	public int getExpertiseId() {
		return expertiseId;
	}

	public void setExpertiseId(int expertiseId) {
		this.expertiseId = expertiseId;
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
