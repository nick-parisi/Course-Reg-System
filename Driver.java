
public class Driver {

	public static void main(String[] args) {
		//model
		EnrollmentModel model = new EnrollmentModel();
		
		//controllers
		StudentController controller = new StudentController(model);
		CourseController controller2 = new CourseController(model);
		EnrollmentController controller3 = new EnrollmentController(model, controller, controller2);
		SaveController controller4 = new SaveController(model);
		model.registerStudentObserver(controller);
		model.registerCourseObserver(controller2);
		model.registerEnrollObserver(controller3);
		
		//views
		StudentView view = new StudentView(controller);
		controller.join(view);
		CourseView view2 = new CourseView(controller2);
		controller2.join(view2);
		EnrollmentView view3 = new EnrollmentView(controller3);
		controller3.join(view3);
		SaveView view4 = new SaveView(controller4);
		controller4.join(view4);
	}

}
