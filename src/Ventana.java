import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ventana {
    private JPanel panel;
    private JButton buscarButton;
    private JButton actualizar;
    private JTextField ced;
    private JTextField Nom;
    private JTextField cell;
    private JTextField correo;

    public Ventana() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try{
                    con = getConection();
                    String query = "SELECT * FROM CLIENTES2 WHERE Ced_Cli = 504871195 ";

                    Statement dialogo  = con.createStatement();
                    ResultSet resultado = dialogo.executeQuery(query);
                    ResultSetMetaData datos = resultado.getMetaData();

                    int columnCount = datos.getColumnCount();
                    JTable table = new JTable();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();


                    if (columnCount > 0){
                        JOptionPane.showMessageDialog(null,"Si se encuentra en la Base");
                        JTable panel = new JTable();
                        DefaultTableModel model1 = (DefaultTableModel) table.getModel();

                        for (int i = 1; i <= columnCount; i++) {
                            model.addColumn(datos.getColumnName(i));
                        }

                        while (resultado.next()) {
                            Object[] row = new Object[columnCount];
                            for (int i = 1; i <= columnCount; i++) {
                                row[i - 1] = resultado.getObject(i);
                            }
                            model.addRow(row);
                        }

                        JFrame frame = new JFrame();
                        frame.add(new JScrollPane(table));
                        frame.setSize(500, 300);
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        resultado.close();
                        dialogo.close();
                        con.close();
                    }else {
                        JOptionPane.showMessageDialog(null, "No se encuentra en la Base");
                    }



                }catch (HeadlessException | SQLException f){
                    System.err.println(f);
                }

            }

        });


        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try{
                    con = getConection();
                    String query = "UPDATE CLIENTES2  SET  Ced_Cli = 504871195 ";







                } catch (HeadlessException f) {
                    System.err.println(f);

                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static Connection getConection() {
        Connection con = null;
        String base = "INFORMACION";
        String url = "jdbc:mysql://localhost:/" + base;
        String user = "root";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }

}
