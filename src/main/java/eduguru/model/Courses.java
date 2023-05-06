package eduguru.model;

import java.sql.Date;

public class Courses {

	protected int courseId;
	protected String title;
	protected Boolean isPlaid;
	protected double price;
	protected String headline;
	protected double avgRating;
	protected int numLectures;
	protected double contentLengthMin;
	protected Date publishedTime;
	protected Date lastUpdateDate;
	protected CourseCategory courseCategory;
	protected String language;

	public enum CourseCategory {

		DEVELOPMENT("Development"), IT_SOFTWARE("It & Software"),
		TEACHING_ACADEMICS("Teaching & Academics"), BUSINESS("Business"),
		PERSONAL_DEVELOPMENT("Personal Development"), DESING("Design"),
		HEALTH_FITNESS("Health & Fitness"), MARKETING("Marketing"),
		LIFESTYLE("Lifestyle"), FINANCE_ACCOUNTING("Finance & Accounting"),
		OFFICE_PRODUCTIVITY("Office Productivity"), MUSIC("Music"),
		PHOTOGRAPHY_VIDEO("Photography & Video");

		protected final String courseCategory;

		CourseCategory(final String courseCategory) {
			this.courseCategory = courseCategory;
		}

		public String getCourseCategory() {
			return this.courseCategory;
		}

		public static CourseCategory fromString(String text) {
			for (CourseCategory c : CourseCategory.values()) {
				if (c.courseCategory.equalsIgnoreCase(text)) {
					return c;
				}
			}
			return null;
		}
	}

	public Courses(int courseId, String title, Boolean isPlaid, double price,
			String headline, double avgRating, int numLectures,
			double contentLengthMin, Date publishedTime, Date lastUpdateDate,
			CourseCategory courseCategory, String language) {
		this.courseId = courseId;
		this.title = title;
		this.isPlaid = isPlaid;
		this.price = price;
		this.headline = headline;
		this.avgRating = avgRating;
		this.numLectures = numLectures;
		this.contentLengthMin = contentLengthMin;
		this.publishedTime = publishedTime;
		this.lastUpdateDate = lastUpdateDate;
		this.courseCategory = courseCategory;
		this.language = language;
	}

	public Courses(String title, Boolean isPlaid, double price, String headline,
			double avgRating, int numLectures, double contentLengthMin,
			Date publishedTime, Date lastUpdateDate,
			CourseCategory courseCategory, String language) {
		this.title = title;
		this.isPlaid = isPlaid;
		this.price = price;
		this.headline = headline;
		this.avgRating = avgRating;
		this.numLectures = numLectures;
		this.contentLengthMin = contentLengthMin;
		this.publishedTime = publishedTime;
		this.lastUpdateDate = lastUpdateDate;
		this.courseCategory = courseCategory;
		this.language = language;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsPlaid() {
		return isPlaid;
	}

	public void setIsPlaid(Boolean isPlaid) {
		this.isPlaid = isPlaid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public int getNumLectures() {
		return numLectures;
	}

	public void setNumLectures(int numLectures) {
		this.numLectures = numLectures;
	}

	public double getContentLengthMin() {
		return contentLengthMin;
	}

	public void setContentLengthMin(double contentLengthMin) {
		this.contentLengthMin = contentLengthMin;
	}

	public Date getPublishedTime() {
		return publishedTime;
	}

	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", title=" + title
				+ ", isPlaid=" + isPlaid + ", price=" + price + ", headline="
				+ headline + ", avgRating=" + avgRating + ", numLectures="
				+ numLectures + ", contentLengthMin=" + contentLengthMin
				+ ", publishedTime=" + publishedTime + ", lastUpdateDate="
				+ lastUpdateDate + ", courseCategory=" + courseCategory
				+ ", language=" + language + "]";
	}
}
