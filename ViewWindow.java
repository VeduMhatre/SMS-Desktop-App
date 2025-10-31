import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class ViewWindow extends JFrame{
	Container c;
	TextArea taData;
	JButton btnBack;
	ViewWindow() {
    c = getContentPane();
    c.setLayout(null);
    c.setBackground(new Color(250,247,243));

    taData = new TextArea(15, 20);
    btnBack = new JButton("Back to Main");

    Font f = new Font("Arial", Font.BOLD, 24);
    taData.setFont(f);
    btnBack.setFont(f);

    taData.setBounds(100, 20, 650, 350);
    btnBack.setBounds(350, 400, 200, 40);

    btnBack.setBackground(new Color(48, 179, 209));
    c.add(taData);
    c.add(btnBack);

    btnBack.addActionListener(ae -> {
        new MainWindow();
        dispose();
    });

    try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection con = DbConfig.getConnection();

        String sql = "SELECT * FROM student";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            int rno = rs.getInt("rno");
            String name = rs.getString("name");
	    int sub1 = rs.getInt("sub1");
	    int sub2 = rs.getInt("sub2");
	    int sub3 = rs.getInt("sub3");

            sb.append("Roll No: ").append(rno).append(", Name: ").append(name).append(", Subj 1: ").append(sub1)
  		.append(", Subj 2: ").append(sub2)
  		.append(", Subj 3: ").append(sub3)
 		 .append("\n");

        }
        System.out.println("Data fetched:\n" + sb); // For debugging
        taData.setText(sb.toString());
        taData.repaint();

        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(c, "Issue: " + e, "DB Error", JOptionPane.ERROR_MESSAGE);
    }

    setSize(850, 500);
    setTitle("View Window");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
}
}
