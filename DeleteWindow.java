import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
class DeleteWindow extends JFrame{
	Container c;
	JLabel labRno;
	JTextField txtRno;
	JButton btnDelete, btnBack;
	
	DeleteWindow(){
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(250,247,243));
		
		labRno = new JLabel("Enter Roll no. To Delete Record ");
		txtRno = new JTextField(10);
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");

	
		Font f = new Font("Georgia",Font.BOLD,24);
		labRno.setFont(f);
		txtRno.setFont(f);
		btnDelete.setFont(f);
		btnBack.setFont(f);

		labRno.setBounds(130,25,450,40);
		txtRno.setBounds(200,70,200,40);
		btnDelete.setBounds(120,125,180,40);
		btnBack.setBounds(310,125,180,40);

		labRno.setForeground(Color.BLUE);
	
		btnDelete.setBackground(new Color(48, 179, 209));
		btnBack.setBackground(new Color(48, 179, 209));
		
		c.add(labRno);
		c.add(txtRno);
		c.add(btnDelete);
		c.add(btnBack);

		ActionListener a = (ae) ->{
			MainWindow mw = new MainWindow();
			dispose();
		};
		btnBack.addActionListener(a);
		
		ActionListener d = (ae) ->{
			int rno = Integer.parseInt(txtRno.getText());	
		
			try{
				//Load Driver
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

				//Make Connection
				Connection con = DbConfig.getConnection();
			
				//do something
				String sql = "delete from student where rno=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1,rno);

				int rowsDeleted = pst.executeUpdate();
               			if (rowsDeleted > 0) {
                  		  	JOptionPane.showMessageDialog(c, "Record Deleted Successfully");
                		} 
				else {
                    			JOptionPane.showMessageDialog(c, "No Record Found with Roll No " + rno, "Warning", JOptionPane.WARNING_MESSAGE);
             			}
				txtRno.setText("");
                		txtRno.requestFocus();
				pst.close();
				con.close();
				}

				catch(SQLException e){
   				 	JOptionPane.showMessageDialog(c,"Issue: " + e,"Error",JOptionPane.ERROR_MESSAGE);
				}

		
		};
		btnDelete.addActionListener(d);

		setTitle("Delete Window");
		setSize(630, 270);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}