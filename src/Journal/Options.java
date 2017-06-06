package Journal;

import MySql.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static MySql.DBConnection.*;

/**
 * Created by Nika on 14.04.2017.
 */
public class Options extends JFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton открытьЗаписьButton;
    private JButton удалитьЗаписьButton;
    private JButton создатьНовуюЗаписьButton;
    private JPanel panel1;
    private JButton вернутсяВГлавноеМенюButton;
    private JButton выходButton;
    private JList list4;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    final DefaultListModel listModel = new DefaultListModel();

    ArrayList <String> list1 = new ArrayList<String>();

    public Options(final DBConnection connect) {

        panel1.setBackground(Color.YELLOW);
        setContentPane(panel1);
        setPreferredSize(new Dimension(500,600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        pack();
        setVisible(true);
        list4.setLayoutOrientation(JList.VERTICAL);
        list4.setModel(listModel);

        Font font = new Font("Arial", Font.PLAIN, 18);
        l1.setFont(font);
        l3.setFont(font);
        l2.setFont(font);

        DBConnection.rs = query("SELECT * from Groups");

        try {

            while (rs.next()) {
                String predmetu = rs.getString("Gruppu");
                comboBox1.addItem(predmetu);
            }
            DBConnection.rs = query("SELECT * from PR");
            while (rs.next()) {
                String predmetu = rs.getString("predmet_PR");
                comboBox2.addItem(predmetu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        // переход к новому окну
        создатьНовуюЗаписьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String vuborGroups = (String) comboBox1.getSelectedItem();
                String vuborPredmets = (String) comboBox2.getSelectedItem();
                String nametable = vuborGroups + vuborPredmets;
                String createTableSQL = "CREATE TABLE IF NOT EXISTS " + nametable + "(id int(100) NOT NULL auto_increment, PRIMARY KEY (id))";
                updateQuery(createTableSQL);

                updateQuery("ALTER TABLE " + nametable + " ADD students varchar(100) NOT NULL");

                try {

                    DBConnection.rs = connect.query("SELECT * from " + vuborGroups);
                    while (rs.next()) {
                        String students = rs.getString("student");
                        list1.add(students);
                        System.out.println("Student " +students);
                    }

                    for(int i = 0; i< list1.size(); i++)
                    {
                        String stud = list1.get(i);
                        updateQuery("INSERT INTO " + nametable + " (students)VALUES('" + stud + "')");
                        System.out.println (list1.get(i));
                    }
                    listModel.addElement(nametable);
                }
                    catch( Exception r)
                    {r.printStackTrace();}
                new CreateJournal(nametable);
                System.out.println("Таблица созданна!");
            }
        });

        // открыть формму журнала
        открытьЗаписьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nametable = (String) list4.getSelectedValue();
                new OpenForm(nametable, connect);
            }
        });

        //удалить
        удалитьЗаписьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //String vuborGroups = (String) comboBox1.getSelectedItem();
               // String vuborPredmets = (String) comboBox2.getSelectedItem();
                //String nametable = vuborGroups + vuborPredmets;

                String nametable = (String) list4.getSelectedValue();

                updateQuery("DROP TABLE " + nametable + ";");
                System.out.println("данные удаленны");
            }
        });


        вернутсяВГлавноеМенюButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Vhod.Menu(connect);
                dispose();
            }
        });


        выходButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit?", "Выйти и сохранить",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    System.exit(0);
                }
            }
        });
    }

}





