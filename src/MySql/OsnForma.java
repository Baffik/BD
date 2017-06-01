package MySql;

import Journal.DataTable;
import Journal.Options;
import Vhod.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import static MySql.DBConnection.*;

/**
 * Created by Nika on 07.03.2017.
 */
public class OsnForma extends JFrame {


    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JButton добавитьButton;
    private JButton удалитьЗаписьButton;
    private JButton удалитьВсеButton;
    private JComboBox comboBox1;
    private JButton добавитьСтудентовКГруппеButton;
    private JButton добавитьButton1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JButton показатьButton;
    private JTable table2;
    private JButton удалитьButton;
    private JTable table3;
    private JButton добавитьГруппуButton;
    private JButton удалитьГруппуButton;
    private JButton удалитьВсёButton;
    private JButton вернутсяВГлавноеМенюButton;
    private JButton выходButton;

    public OsnForma(final DBConnection connect) {

        setName("admin");
        setContentPane(panel);
        setResizable(false);// нельзя менять размер окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //размещение по середине экрана
        setPreferredSize(new Dimension(500,600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        pack();
        setVisible(true);

        //установить окно поверх других окон
      //  setAlwaysOnTop( true );
       // setLocationByPlatform( true );
        //создаем коннект
     //  final DBConnection connect = new DBConnection("localhost","","","Diplom");
      //  connect.initProperties();;
        //connect.init();


        //отображение предметов в таблицу
        //DataTable DT = new DataTable("PR");
        TableADDPR PR = new TableADDPR();
        PR.ADDPR(connect);
        table1.setModel(PR);

        // JScrollPane Table1Scrol = new JScrollPane(table1);
         // Table1Scrol.setPreferredSize(new Dimension(100, 100));


         // Добавляем группу к таблице
          добавитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new KnopkaDobavit();
            }
        });

          //удаляем группу по ИД
        удалитьЗаписьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletePredmet();
            }
        });

        // knopka dlya ochistki zapisei grupp
        удалитьВсеButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuery("TRUNCATE TABLE PR");
                //updateQuery("TRUNCATE TABLE System");
                System.out.println("Записи удаленны");
            }
        });

            //вибираем файл для добавления в группу студентов
        добавитьСтудентовКГруппеButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Выберите файл группы: ");
                fc.setCurrentDirectory(new java.io.File("/java/")); // start at application current directory
            //    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // posmotret' dlya faila
                int returnVal = fc.showOpenDialog(panel);
                if(returnVal == JFileChooser.APPROVE_OPTION) {

                    File file = fc.getSelectedFile();
                    JOptionPane.showMessageDialog(panel, "ОК");

                    String nametable = (String)comboBox1.getSelectedItem();
                    ADDFailu(file, nametable);

                } else {
                    JOptionPane.showMessageDialog(panel, "Файл не выбран");
                }
            }
        });


        // Добавляем в комбобокс группы из таблици

        try {
            DBConnection.rs = query("SELECT * from Groups");
            while (rs.next()) {
                String predmetu = rs.getString("Gruppu");
                comboBox1.addItem(predmetu);
                comboBox2.addItem(predmetu);
                 //создаем таблици групп
                CreateTableRB(predmetu);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // добавляем студента к группе по КБ
        добавитьButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nametable = (String)comboBox1.getSelectedItem();
                String student = textField1.getText();
                updateQuery("INSERT INTO " + nametable+ "(student)VALUES('"+student+"')");
                textField1.setText("");
            }
        });

        //удаляем студента из группы
        удалитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nametable = (String)comboBox1.getSelectedItem();
                String s = textField1.getText();

                updateQuery("DELETE from " +nametable+ " WHERE student = '" + s +"';");
                //  updateQuery("ALTER TABLE PR AUTO_INCREMENT=0;");
                System.out.println ("Студент удалён");
            }
        });

      //для вибора групп с КБ и отображения их в таблици
        показатьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String vubortablici = (String)comboBox2.getSelectedItem();
                GroupTableModel GTM = new GroupTableModel();
                GTM.VuvodTable(connect, vubortablici);
                table2.setModel(GTM);
            }
        });

        // работа с группами
       TableADDGroups groupstm = new TableADDGroups();
        groupstm.ADDGroups(connect,"Groups");
       // DataTable groupstm = new DataTable("Groups");
        table3.setModel(groupstm);

        добавитьГруппуButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new ADDGroups();
            }
        });

        удалитьГруппуButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteGroups();
            }
        });

        удалитьВсёButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuery("TRUNCATE TABLE Groups");
                System.out.println("Записи удаленны");
            }
        });

      // В из программы
        выходButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

       //бек
        вернутсяВГлавноеМенюButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Vhod.Menu(connect);
               dispose();
               // setVisible(false);
            }
        });
    }




}
