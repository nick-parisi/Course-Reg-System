import java.io.Serializable;
import java.util.ArrayList;


public class Enrollment implements Serializable {
	private Student student;
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public Enrollment(Student student) {
		this.student = student;
	}
	
	public void addCourse(Course cs) {
		courses.add(cs);
	}
	
	public void dropCourse(Course cs) {
		courses.remove(cs);
	}
	
	public Student getStudent() { return student; }
	public ArrayList<Course> getCourses() { return courses; }
}
