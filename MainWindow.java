import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class MainWindow extends JFrame{
	Container c;
	JButton btnAdd, btnView, btnUpdate, btnDelete;
	JLabel labHead;
	MainWindow(){
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(250,247,243));
		
		labHead = new JLabel("Student Management System");
		btnAdd = new JButton("Add Student");
		btnView = new JButton("View Students");
		btnUpdate = new JButton("Update Student");
		btnDelete = new JButton("Delete Student");
		
		Font f = new Font("Georgia",Font.BOLD,24);
		labHead.setFont(f);
		btnAdd.setFont(f);
		btnView.setFont(f);
		btnUpdate.setFont(f);
		btnDelete.setFont(f);
	
		labHead.setBounds(175,20,380,55);
		btnAdd.setBounds(80,85,250,45);
		btnView.setBounds(350,85,250,45);
		btnUpdate.setBounds(80,140,250,45);
		btnDelete.setBounds(350,140,250,45);

		labHead.setFont(new Font("Arial", Font.BOLD, 24)); 
		btnAdd.setBackground(new Color(215, 180, 210));
		btnView.setBackground(new Color(215, 180, 210));
		btnUpdate.setBackground(new Color(144, 213, 255));
		btnDelete.setBackground(new Color(144, 213, 255));

		
		c.add(labHead);
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		ActionListener a = (ae)->{
			AddWindow aw = new AddWindow();
			dispose();
		};
		btnAdd.addActionListener(a);
		
		ActionListener b = (ae)->{
			ViewWindow aw = new ViewWindow();
			dispose();
		};
		btnView.addActionListener(b);
	
		ActionListener saveAction = (ae)->{
			UpdateWindow uw = new UpdateWindow();
			dispose();
		};
		btnUpdate.addActionListener(saveAction);
		
		ActionListener d = (ae)->{
			DeleteWindow dw = new DeleteWindow();
			dispose();
		};
		btnDelete.addActionListener(d);
		
		setSize(670,300);
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}	
}
