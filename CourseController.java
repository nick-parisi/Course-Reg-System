import java.io.IOException;
import java.util.ArrayList;


public class CourseController implements CourseObserver {
	private EnrollmentModel model;
	private CourseView view;
	
	public CourseController(EnrollmentModel model) {
		this.model = model;
	}
	
	public void join(CourseView view) {
		this.view = view;
	}

	
	public ArrayList<Course> getCourses() { return model.getCourses(); }
	
	public String[] getData() {
		return view.getData();
	}

	public void update(ArrayList<Course> courses) {
		view.refreshTable(courses);
	}
	
	public void update(String code, String title) {
		model.addCourse(code, title);
	}
}
