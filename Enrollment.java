
public class Enrollment {

	private Course course;
	private Student student;
	private int grade;
	
	public Enrollment(Course course, Student student, int grade) {
		super();
		this.course = course;
		this.student = student;
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
