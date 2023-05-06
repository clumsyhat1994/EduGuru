package eduguru.model;

import java.sql.Date;

public class Comments {
	
	protected int commentId;
	protected Students student;
	protected Courses course;
	protected String displayNameComments;
	protected String commentText;
	protected double commentRating;
	protected Date commentDate;

	public Comments(int commentId, Students student, Courses course,
			String displayNameComments, String commentText,
			double commentRating, Date commentDate) {
		this.commentId = commentId;
		this.student = student;
		this.course = course;
		this.displayNameComments = displayNameComments;
		this.commentText = commentText;
		this.commentRating = commentRating;
		this.commentDate = commentDate;
	}

	public Comments(Students student, Courses course,
			String displayNameComments, String commentText,
			double commentRating, Date commentDate) {
		this.student = student;
		this.course = course;
		this.displayNameComments = displayNameComments;
		this.commentText = commentText;
		this.commentRating = commentRating;
		this.commentDate = commentDate;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public String getDisplayNameComments() {
		return displayNameComments;
	}

	public void setDisplayNameComments(String displayNameComments) {
		this.displayNameComments = displayNameComments;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public double getCommentRating() {
		return commentRating;
	}

	public void setCommentRating(double commentRating) {
		this.commentRating = commentRating;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", student=" + student
				+ ", course=" + course + ", displayNameComments="
				+ displayNameComments + ", commentText=" + commentText
				+ ", commentRating=" + commentRating + ", commentDate="
				+ commentDate + "]";
	}
}
