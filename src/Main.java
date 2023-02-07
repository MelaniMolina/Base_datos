import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/INFORMACION", "root", "12345");
            String query = "SELECT * FROM CLIENTES2 WHERE Ced_Cli = 504871195 ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Create JTable and set model
            JTable table = new JTable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Add columns to table model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            // Add rows to table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Display JTable in JFrame
            JFrame frame = new JFrame();
            frame.add(new JScrollPane(table));
            frame.setSize(500, 300);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
