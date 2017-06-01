package Journal;

import MySql.DBConnection;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

/**
 * Created by Nika on 17.04.2017.
 */
public class OpenForm extends JFrame {
    private JTable table1;
    private JPanel panel1;
    public static DataTable Jmodel = null;

    public OpenForm(String nametable, DBConnection connect)
    {
        setContentPane(panel1);
       // setResizable(false);
        setPreferredSize(new Dimension(500,600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        pack();
        setVisible(true);
      //  final DBConnection connect = new DBConnection("localhost", "", "", "Diplom");
       // connect.initProperties();
       // connect.init();

        //создаем коннект
       // final DBConnection connect=null;
       // connect.setMysqlConnect();
       //  DataTable Jmodel = new DataTable(connect, nametable);
       // table1.setModel(Jmodel);

        Jmodel = new DataTable(nametable);
        table1.setModel(Jmodel);
    }
}
