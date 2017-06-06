package Vhod;

import MySql.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MySql.DBConnection.updateQuery;

/**
 * Created by Nika on 02.04.2017.
 */
public class GlavnoeOkno extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton входButton;
    private JLabel L4;
    private JTextField textField3;
    private JLabel L6;
    private JLabel l5;
    private JLabel l3;
    private JLabel L2;
    private JLabel L1;
    private JLabel LK;

    public GlavnoeOkno() {


        setContentPane(panel1);
        panel1.setBackground(Color.YELLOW);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        setPreferredSize(new Dimension(500,600));
        pack();
        setVisible(true);
        Font font = new Font("Arial", Font.PLAIN, 18);
        L1.setFont(font);
        l5.setFont(font);
        l3.setFont(font);
        ImageIcon icon = new ImageIcon(GlavnoeOkno.class.getResource("/1.png"));
        LK.setIcon(icon);

        textField3.setText("Diplom");
        textField1.setText("root");

      //vhod v programmu
        входButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            String login = textField1.getText();
            String parol = textField2.getText();
            String nameDB = textField3.getText();
                final DBConnection connect = new DBConnection("localhost",login,parol,nameDB);
                // Создаем таблици групп и предметов
                String createPredmet =  "CREATE TABLE IF NOT EXISTS PR(ID_PR int(100) NOT NULL auto_increment,predmet_PR varchar(100) NOT NULL, PRIMARY KEY (ID_PR))" ;
                updateQuery(createPredmet);
                String createGroups =  "CREATE TABLE IF NOT EXISTS Groups(ID_Groups int(100) NOT NULL auto_increment,Gruppu varchar(100) NOT NULL, PRIMARY KEY (ID_Groups))" ;
                updateQuery(createGroups);

              //  if((login.equals("root"))&& (parol.equals("")) && (nameDB.equals("Diplom")))
              //  {
                  // new OsnForma();
                    new Menu(connect);
                    setVisible(false);
              /*  }
                else {
                    if (!login.equals("root"))
                        L2.setText("Неверно введен логин, повторите попытку");

                    if (!parol.equals(""))
                        L4.setText("Неверно введен пароль, повторите попытку");

                    if (!nameDB.equals("Diplom"))
                        L6.setText("Неверно введенная БД, повторите попытку");
                }
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                */
            }
        });
    }
}
