package eduguru.model;

public class Flags {
	protected int flagId;
	protected Students student;
	protected Courses course;

	public Flags(int flagId, Students student, Courses course) {
		this.flagId = flagId;
		this.student = student;
		this.course = course;
	}

	public Flags(Students student, Courses course) {
		this.student = student;
		this.course = course;
	}

	public int getFlagId() {
		return flagId;
	}

	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

}
