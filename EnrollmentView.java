import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class EnrollmentView extends JFrame {
	private EnrollmentController controller;
	private JTable enrollTable;
	private JScrollPane enrollPane;
	private JButton add;
	private JButton drop;
	private JButton select;
	
	private Student selectedStudent;
	
	private String[][] data;
	
	public EnrollmentView(final EnrollmentController controller) {
		this.controller = controller;
		setTitle("Select a student");
		setLayout(new GridLayout(2,1));
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	controller.add(selectedStudent);
	        }
	    });
		
		drop = new JButton("DROP");
		drop.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	controller.drop(selectedStudent);
	        }
	    });
		
		select = new JButton("SELECT STUDENT");
		select.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//get student and populate table
	        	
	        	Enrollment info = controller.getStudent();
	        	
	        	selectedStudent = info.getStudent();
	        	
	        	populate(info);
	        }
	    });
		
		String[] columnNames = {"Code", "Title" };
		data = new String[10][2];
		enrollTable = new JTable(data, columnNames);
		enrollPane = new JScrollPane(enrollTable);
		
		topPanel.add(enrollPane); topPanel.add(add); topPanel.add(drop);
		bottomPanel.add(select);
		add(topPanel); add(bottomPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void populate(Enrollment e) {
		//must clear data WITHOUT making it a new object
		//Otherwise when loading new data there will be leftover data
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 2; y++) {
				data[x][y] = null;
			}
		}
				
		setTitle("Enrollments for " + e.getStudent().getName());
		ArrayList<Course> courses = e.getCourses();
		int count = 0;
		for (Course cs : courses) {
			data[count][0] = cs.getCode();
			data[count][1] = cs.getTitle();
			count++;
		}
		enrollTable.repaint();
		enrollPane.repaint();
	}
	
	public String[] getData() {
		int row = enrollTable.getSelectedRow();
		String codez = data[row][0]; 
		String titlez = data[row][1];  
		
		String[] info = new String[2];
		info[0] = codez;
		info[1] = titlez;
		
		return info;
	}
	
	public void refreshTable(ArrayList<Enrollment> e) {
		
		for (Enrollment enroll : e) {
			if (enroll.getStudent().equals(selectedStudent)) {
				populate(enroll);
			}
		}
	}
}
