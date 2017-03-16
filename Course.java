
public class Course {

	private int idCourse;
	private String courseName;
	private String professorName;
	private int study_year;
	private int numberOfStudents;
	

	public Course(int idCourse, String courseName, String professorName, int study_year, int numberOfStudents) {
		super();
		this.idCourse = idCourse;
		this.courseName = courseName;
		this.professorName = professorName;
		this.study_year = study_year;
		this.numberOfStudents = numberOfStudents;
	}	



	public int getIdCourse() {
		return idCourse;
	}


	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getProfessorName() {
		return professorName;
	}


	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}


	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}



	public int getStudy_year() {
		return study_year;
	}



	public void setStudy_year(int study_year) {
		this.study_year = study_year;
	}



	@Override
	public String toString() {
		return "Course [idCourse=" + idCourse + ", courseName=" + courseName + ", professorName=" + professorName
				+ ", study_year=" + study_year + ", numberOfStudents=" + numberOfStudents + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + idCourse;
		result = prime * result + numberOfStudents;
		result = prime * result + ((professorName == null) ? 0 : professorName.hashCode());
		result = prime * result + study_year;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (idCourse != other.idCourse)
			return false;
		if (numberOfStudents != other.numberOfStudents)
			return false;
		if (professorName == null) {
			if (other.professorName != null)
				return false;
		} else if (!professorName.equals(other.professorName))
			return false;
		if (study_year != other.study_year)
			return false;
		return true;
	}
}
