import java.io.IOException;
import java.util.ArrayList;


public class StudentController implements StudentObserver {
	private EnrollmentModel model;
	private StudentView view;
	
	public StudentController(EnrollmentModel model) {
		this.model = model;
	}
	
	public void join(StudentView view) {
		this.view = view;
	}

	public void update(ArrayList<Student> students) {
		view.refreshTable(students);	
	}
	
	public void debugSave() throws IOException {
		model.save();
		System.exit(0);
	}
	
	public void update(String name, String gradyear) {
		model.addStudent(name, Integer.parseInt(gradyear));
	}
	
	public String[] getData() {
		return view.getData();
	}
	
	public ArrayList<Student> getStudents() { return model.getStudents(); }
}
