package eduguru.model;

public class Instructors extends Users {

	protected String fullName;

	public Instructors(String username, String password, String email,
			String fullName) {
		super(username, password, email);
		this.fullName = fullName;
	}

	public Instructors(String username, String password, String email) {
		super(username, password, email);
	}

	public Instructors(String username) {
		super(username);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Instructors [fullName=" + fullName + ", username=" + username
				+ ", password=" + password + ", email=" + email + "]";
	}
}
