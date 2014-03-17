import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;



public class StudentView extends JFrame {
	private StudentController controller;
	private JButton newStudent;
	//debug save
	private JButton debugSave;
	private JTextField name;
	private JTextField grad;
	private JTable studentTable;
	private JScrollPane studentPane;
	
	private String[][] data = new String[10][3];
	private int count = 0;
	
	public StudentView(final StudentController controller) {
		this.controller = controller;
		setTitle("Student Info");
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		newStudent = new JButton("NEW STUDENT");
		newStudent.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	controller.update(name.getText(), grad.getText());
	        }
	    });
		
		debugSave = new JButton("DEBUG SAVE");
		debugSave.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	try {
					controller.debugSave();
				} catch (IOException e1) {
					System.out.println("WHY IS THIS HAPPENING");
				}
	        }
	    });
		
		name = new JTextField(10);
		grad = new JTextField(10);
		
		topPanel.add(newStudent); topPanel.add(name); topPanel.add(grad); topPanel.add(debugSave);
		
		String[] columnNames = {"ID", "Name", "GradYear"};
		
		//initialize the data
		ArrayList<Student> students = controller.getStudents();
		for (Student std : students) {
			data[count][0] = Integer.toString(std.getID());
			data[count][1] = std.getName();
			data[count][2] = Integer.toString(std.getGradYear());
			count++;
		}
		
		studentTable = new JTable(data, columnNames);
		studentPane = new JScrollPane(studentTable);
		//studentPane.add(studentTable);
		
		bottomPanel.add(studentTable);
		
		setLayout(new GridLayout(2,1));
		add(topPanel); add(bottomPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public String[] getData() {
		int row = studentTable.getSelectedRow();
		String idz = data[row][0]; 
		String namez = data[row][1]; 
		String yearz = data[row][2]; 
		
		String[] info = new String[3];
		info[0] = idz;
		info[1] = namez;
		info[2] = yearz;
		
		return info;
	}
	
	public void refreshTable(ArrayList<Student> students) {
		count = 0;
		for (Student std : students) {
			data[count][0] = Integer.toString(std.getID());
			data[count][1] = std.getName();
			data[count][2] = Integer.toString(std.getGradYear());
			count++;
			studentTable.repaint();
			studentPane.repaint();
		}
		
	}
	
	/*public void actionPerformed(ActionEvent e) {
		System.out.println("made it");
		controller.update(name.getText(), grad.getText());
	}*/
}
