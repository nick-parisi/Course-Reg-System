import java.io.*;
import java.util.*;



public class EnrollmentModel {
	private ArrayList<Student> students;
	private ArrayList<Course> courses;
	private ArrayList<Enrollment> enrollment;
	
	private int id = 1;
	private ArrayList<StudentObserver> studentControllers = new ArrayList<StudentObserver>();
	private ArrayList<CourseObserver> courseControllers = new ArrayList<CourseObserver>();
	private ArrayList<EnrollObserver> enrollControllers = new ArrayList<EnrollObserver>();
	//May need to rethink enroll observers
	
	
	public EnrollmentModel() {
		load();
	}
	
	public void addStudent(String name, int gradyear) {
		Student std = new Student(id, name, gradyear);
		id++;
		students.add(std);
		notifyStudentObservers();
		//Should also create an enrollment for this student
		Enrollment e = new Enrollment(std);
		enrollment.add(e);
	}
	
	public void addCourse(String code, String title) {
		Course cs = new Course(code, title);
		courses.add(cs);
		notifyCourseObservers();
	}
	
	
	public Enrollment findStudentEnrollment(String[] info) {
		Student temp = null;
		for (Student st : students) {
			if(st.getName().equals(info[1]) && Integer.toString(st.getGradYear()).equals(info[2])) {
				temp = st;
			}
		}
		for (Enrollment en : enrollment) {
			if (en.getStudent().equals(temp)) {
				return en;
			}
		}
		return null;
	}
	
	public void addEnrollment(Student st, String[] info) {
		//first find the course
		Course toAdd = null;
		for (Course cs : courses) {
			if (cs.getCode().equals(info[0]) && cs.getTitle().equals(info[1])) {
				toAdd = cs;
			}
		}
		//now add the course to the students enrollment
		for (Enrollment en : enrollment) {
			if (en.getStudent().equals(st)) {
				en.addCourse(toAdd);
			}
		}
		notifyEnrollObservers();
	}
	
	public void dropEnrollment(Student st, String[] info) {
		//first find the course
		Course toDrop = null;
		for (Course cs : courses) {
			if (cs.getCode().equals(info[0]) && cs.getTitle().equals(info[1])) {
				toDrop = cs;
			}
		}
		//now add the course to the students enrollment
		for (Enrollment en : enrollment) {
			if (en.getStudent().equals(st)) {
				en.dropCourse(toDrop);
			}
		}
		notifyEnrollObservers();
	}
	
	public void registerStudentObserver(StudentObserver obs) {
		studentControllers.add(obs);
	}
	
	public void registerCourseObserver(CourseObserver obs) {
		courseControllers.add(obs);
	}
	
	public void registerEnrollObserver(EnrollObserver obs) {
		enrollControllers.add(obs);
	}
	
	
	public void notifyStudentObservers() {
		for (StudentObserver o : studentControllers) {
			o.update(students);
		}
	}
	
	public void notifyEnrollObservers() {
		for (EnrollObserver o : enrollControllers) {
			o.update(enrollment);
		}
	}
	
	public void notifyCourseObservers() {
		for (CourseObserver o : courseControllers) {
			o.update(courses);
		}
	}
	
	public ArrayList<Student> getStudents() { return students; }
	public ArrayList<Course> getCourses() { return courses; }
	
	public void load() {
		try {
			File f = new File("data.dat");
			if (f.isFile()) {
				FileInputStream is = new FileInputStream(f);
				ObjectInputStream input = new ObjectInputStream(is);
				this.students = (ArrayList<Student>) input.readObject();
				this.courses = (ArrayList<Course>) input.readObject();
				this.enrollment = (ArrayList<Enrollment>) input.readObject();
				input.close();
				notifyStudentObservers();
				notifyCourseObservers();
				notifyEnrollObservers();
			}
			else {
				//there is no data file to load from
				//set lists to new lists
				this.students = new ArrayList<Student>();
				this.courses = new ArrayList<Course>();
				this.enrollment = new ArrayList<Enrollment>();
			}
			
		} 
		catch (IOException | ClassNotFoundException e) {
			//there is no data file to load from
			//set lists to new lists
		
			this.students = new ArrayList<Student>();
			this.courses = new ArrayList<Course>();
			this.enrollment = new ArrayList<Enrollment>();
		}
	}
	
	public void save() throws IOException {
		FileOutputStream os = new FileOutputStream("data.dat");
		ObjectOutputStream output = new ObjectOutputStream(os);
		output.writeObject(students);
		output.writeObject(courses);
		output.writeObject(enrollment);
		output.close();
		System.exit(0);
	}
}
