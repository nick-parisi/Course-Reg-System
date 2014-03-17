import java.io.Serializable;


public class Student implements Serializable {
	private int id;
	private String name;
	private int gradyear;
	
	public Student(int id, String name, int gradyear) {
		this.id = id; this.name = name; this.gradyear = gradyear;
	}
	
	public boolean equals(Student other) {
		if (other.getName().equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getID() { return id; }
	public String getName() { return name; }
	public int getGradYear() {return gradyear; }
	
}
