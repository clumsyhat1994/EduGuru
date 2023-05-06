package eduguru.model;

public class Subscribes {

	protected int subscribeId;
	protected Students students;
	protected Courses courses;

	public Subscribes(int subscribeId, Students students, Courses courses) {
		this.subscribeId = subscribeId;
		this.students = students;
		this.courses = courses;
	}

	public Subscribes(Students students, Courses courses) {
		this.students = students;
		this.courses = courses;
	}

	public int getSubscribeId() {
		return subscribeId;
	}

	public void setSubscribeId(int subscribeId) {
		this.subscribeId = subscribeId;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Subscribes [subscribeId=" + subscribeId + ", students="
				+ students + ", courses=" + courses + "]";
	}
}
