import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;



public class CourseView extends JFrame {
	private CourseController controller;
	private JButton newCourse;
	
	private JTextField code;
	private JTextField title;
	private JTable courseTable;
	private JScrollPane coursePane;
	
	private String[][] data = new String[10][2];
	private int count = 0;
	
	public CourseView(final CourseController controller) {
		this.controller = controller;
		setTitle("Course Info");
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		newCourse = new JButton("NEW COURSE");
		newCourse.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	controller.update(code.getText(), title.getText());
	        }
	    });
		
		code = new JTextField(10);
		title = new JTextField(10);
		
		topPanel.add(newCourse); topPanel.add(code); topPanel.add(title);
		
		String[] columnNames = {"Course", "Title"};
		
		//initialize the data
		ArrayList<Course> courses = controller.getCourses();
		for (Course cs : courses) {
			data[count][0] = cs.getCode();
			data[count][1] = cs.getTitle();
			count++;
		}
		
		courseTable = new JTable(data, columnNames);
		coursePane = new JScrollPane(courseTable);
		//studentPane.add(studentTable);
		
		bottomPanel.add(coursePane);
		
		setLayout(new GridLayout(2,1));
		add(topPanel); add(bottomPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public String[] getData() {
		int row = courseTable.getSelectedRow();
		String codez = data[row][0]; 
		String titlez = data[row][1];  
		
		String[] info = new String[2];
		info[0] = codez;
		info[1] = titlez;
		
		return info;
	}
	
	public void refreshTable(ArrayList<Course> courses) {
		count = 0;
		for (Course cs : courses) {
			data[count][0] = cs.getCode();
			data[count][1] = cs.getTitle();
			count++;
			courseTable.repaint();
			coursePane.repaint();
		}
		
	}
	
	/*public void actionPerformed(ActionEvent e) {
		System.out.println("made it");
		controller.update(code.getText(), title.getText());
	}*/
}
