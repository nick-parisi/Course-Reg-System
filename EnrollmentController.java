import java.util.ArrayList;


public class EnrollmentController implements EnrollObserver {
	private EnrollmentModel model;
	private EnrollmentView view;
	private StudentController scontroller;
	private CourseController ccontroller;
	
	public EnrollmentController(EnrollmentModel model, StudentController scontroller, CourseController ccontroller) {
		this.model = model;
		this.scontroller = scontroller;
		this.ccontroller = ccontroller;
	}
	
	public void join(EnrollmentView view) { this.view = view; }
	
	public Enrollment getStudent() {
		String[] info = scontroller.getData();
		Enrollment en = model.findStudentEnrollment(info);
		return en;
	}
	
	public void add(Student st) {
		//find course to add
		String[] info = ccontroller.getData();
		model.addEnrollment(st, info);
	}
	
	public void drop(Student st) {
		//find course to drop
		String[] info = view.getData();
		
		model.dropEnrollment(st, info);
	}

	@Override
	public void update(ArrayList<Enrollment> enrollment) {
		view.refreshTable(enrollment);
	}
	
}
