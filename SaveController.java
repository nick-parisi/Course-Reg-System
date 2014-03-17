import java.io.IOException;


public class SaveController {
	private EnrollmentModel model;
	private SaveView view;
	
	public SaveController(EnrollmentModel model) { this.model = model; }
	
	public void join(SaveView view) { this.view = view; }
	
	public void save() throws IOException {
		model.save();
	}
	
}
