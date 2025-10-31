import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
class UpdateWindow extends JFrame{
	Container c;
	JLabel labHead, labRno, labName, labSb1, labSb2, labSb3;
	JTextField txtRno, txtName, txtSb1, txtSb2, txtSb3;
	JButton btnSave, btnBack;

	UpdateWindow(){
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(250,247,243));

		labHead = new JLabel("Update Student Details");
		labRno = new JLabel("Enter Roll No");
		txtRno = new JTextField(10);
		labName	= new JLabel("Enter Name");
		txtName	= new JTextField(10);
		labSb1 = new JLabel("Enter Subject Marks 1");
		txtSb1 = new JTextField(10);
		labSb2 = new JLabel("Enter Subject Marks 2");
		txtSb2 = new JTextField(10);
		labSb3 = new JLabel("Enter Subject Marks 3");
		txtSb3 = new JTextField(10);
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");

		Font f = new Font("Georgia",Font.BOLD,24);
		labRno.setFont(f); 
		txtRno.setFont(f);
		labName.setFont(f);  
		txtName.setFont(f);  
		labSb1.setFont(f); 
		txtSb1.setFont(f); 
		labSb2.setFont(f); 	
		txtSb2.setFont(f);  
		labSb3.setFont(f);
		txtSb3.setFont(f);
		btnSave.setFont(f);
		btnBack.setFont(f);	
		labHead.setFont(f);
		
		labHead.setForeground(Color.BLUE);
		
		labHead.setBounds(250, 5, 350, 40);
		labRno.setBounds(200, 50, 250, 40);   // x=200, y=20,  width=250, height=40
		txtRno.setBounds(450, 50, 200, 40);   // x=450, y=20,  width=200, height=40

		labName.setBounds(200, 100, 250, 40);  // x=200, y=80,  width=250, height=40
		txtName.setBounds(450, 100, 200, 40);  // x=450, y=80,  width=200, height=40

		labSb1.setBounds(150, 150, 280, 40);  // x=150, y=140, width=280, height=40
		txtSb1.setBounds(450, 150, 200, 40);  // x=450, y=140, width=200, height=40

		labSb2.setBounds(150, 200, 280, 40);  // x=150, y=200, width=280, height=40
		txtSb2.setBounds(450, 200, 200, 40);  // x=450, y=200, width=200, height=40

		labSb3.setBounds(150, 250, 280, 40);
		txtSb3.setBounds(450, 250, 200, 40); 

		btnSave.setBounds(180, 315, 200, 50); // x=200, y=330, width=200, height=50
		btnBack.setBounds(440, 315, 200, 50); 

		btnSave.setBackground(new Color(48, 179, 209));
		btnBack.setBackground(new Color(48, 179, 209));
		
		c.add(labHead);
		c.add(labRno);
		c.add(txtRno);
		c.add(labName);
		c.add(txtName);
		c.add(labSb1);
		c.add(txtSb1);
		c.add(labSb2);
		c.add(txtSb2);
		c.add(labSb3);
		c.add(txtSb3);
		c.add(btnSave);
		c.add(btnBack);

		ActionListener a = (ae) -> {
			MainWindow mw = new MainWindow();
			dispose();
		};
		btnBack.addActionListener(a);
		
		ActionListener saveAction = (ae) -> {
			int rno = Integer.parseInt(txtRno.getText());
			String name = txtName.getText();
			int sub1 = Integer.parseInt(txtSb1.getText());
			int sub2 = Integer.parseInt(txtSb2.getText());
			int sub3 = Integer.parseInt(txtSb3.getText());

			try{
				//Load The Driver
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

				//make the connection
				Connection con = DbConfig.getConnection(); 
				
				//do something
				String sql = "update student SET name=?, sub1=?, sub2=?, sub3=? WHERE rno=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, sub1);
				pst.setInt(3, sub2);
				pst.setInt(4, sub3);
				pst.setInt(5, rno);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(c,"Record Updated");

				txtRno.setText("");
				txtName.setText("");
				txtSb1.setText("");
				txtSb2.setText("");
				txtSb3.setText("");
				txtRno.requestFocus();
				
				//close the connection
				con.close();

			}
			catch(SQLException e){
				JOptionPane.showMessageDialog(c,"Issue " + e,"error",JOptionPane.ERROR_MESSAGE);
			}
		};
		btnSave.addActionListener(saveAction);
		
		setTitle("Update Window");
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}