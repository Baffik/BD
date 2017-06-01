package Vhod;

import Journal.Options;
import MySql.DBConnection;
import MySql.OsnForma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/**
 * Created by Nika on 03.04.2017.
 */
public class Menu extends JFrame {
    private JButton посещениеГруппButton;
    private JButton настройкаButton;
    private JButton выходButton;
    private JPanel panel;

    public Menu(final DBConnection connect) {

        setContentPane(panel);
        setResizable(false);
        setPreferredSize(new Dimension(500,600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        pack();
        setVisible(true);

        // вызов подпрограммы добавление студентов, групп и редактирование
        настройкаButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new OsnForma(connect);
                setVisible(false);
            }
        });


     // реализация выхода из программы, с подверждением
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


        посещениеГруппButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Options(connect);
            }
        });
    }

}