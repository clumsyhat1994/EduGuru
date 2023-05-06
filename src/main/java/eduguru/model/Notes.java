package eduguru.model;

public class Notes {
	protected int noteId;
	protected Students student;
	protected Courses course;
	protected String noteText;

	public Notes(int noteId, Students student, Courses course,
			String noteText) {
		this.noteId = noteId;
		this.student = student;
		this.course = course;
		this.noteText = noteText;
	}

	public Notes(Students student, Courses course, String noteText) {
		this.student = student;
		this.course = course;
		this.noteText = noteText;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
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

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

}
