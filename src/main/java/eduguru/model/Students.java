package eduguru.model;

public class Students extends Users {

	protected Boolean cutBudget;
	protected String displayName;

	public Students(String username, String password, String email,
			Boolean cutBudget, String displayName) {
		super(username, password, email);
		this.cutBudget = cutBudget;
		this.displayName = displayName;
	}

	public Students(String username, String password, String email) {
		super(username, password, email);
	}
	
	public Students(String username) {
		super(username);
	}

	public Boolean getCutBudget() {
		return cutBudget;
	}

	public void setCutBudget(Boolean cutBudget) {
		this.cutBudget = cutBudget;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "Students [cutBudget=" + cutBudget + ", displayName="
				+ displayName + ", username=" + username + ", password="
				+ password + ", email=" + email + "]";
	}
}
