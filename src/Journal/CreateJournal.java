package Journal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static MySql.DBConnection.updateQuery;

/**
 * Created by Nika on 14.04.2017.
 */
public class CreateJournal extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JButton подвердитьButton;
    private JPanel panel1;

    public CreateJournal(final String nametable)
    {
        setContentPane(panel1);
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

        System.out.println("Perehod");

        подвердитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox1.isSelected()) {
                    updateQuery(" ALTER TABLE " +nametable+ " ADD BallzaLekcii varchar(100) NOT NULL");
                    System.out.println("Perehod");
                } else {
                    String s = textField1.getText();
                   int kolichestvo = Integer.parseInt(s);
                    for(int i=1; i<=kolichestvo; i++) {
                         int intV=i;
                        String stroka = String.valueOf(intV);
                         String strV = "Urok№"+stroka;
                        updateQuery("ALTER TABLE " + nametable + " ADD "+strV+ " varchar(100) NOT NULL");
                        System.out.println(strV);
                  }
                    textField1.setText("");

                   updateQuery("ALTER TABLE " +nametable+ " ADD SummaBallovzaLekcii varchar(100) NOT NULL");
                   System.out.println(kolichestvo);
                }


                if (checkBox2.isSelected()) {

                    updateQuery("ALTER TABLE " +nametable+ " ADD BallzaPracticheskie varchar(100) NOT NULL");
                    System.out.println("Perehod2");

                } else {

                    String s = textField2.getText();
                    int kolichestvo = Integer.parseInt(s);
                    for(int i=1; i<=kolichestvo; i++) {
                        int intV=i;
                        String stroka = String.valueOf(intV);
                        String strV = "PracticheskoeZanyatie№"+stroka;
                        updateQuery("ALTER TABLE " + nametable + " ADD "+strV+ " varchar(100) NOT NULL");
                        System.out.println(strV);
                    }
                    textField2.setText("");
                    updateQuery("ALTER TABLE " +nametable+ " ADD SummaBallovzaPracticheskie varchar(100) NOT NULL");
                    System.out.println(kolichestvo);

                }



                if (checkBox3.isSelected()) {

                    updateQuery("ALTER TABLE " +nametable+ " ADD BallzaLaboratornue varchar(100) NOT NULL");
                    System.out.println("Perehod");

                } else {

                    String s = textField3.getText();
                    int kolichestvo = Integer.parseInt(s);
                    for(int i=1; i<=kolichestvo; i++) {
                        int intV=i;
                        String stroka = String.valueOf(intV);
                        String strV = "LaboratornoeZanyatie№"+stroka;
                        updateQuery("ALTER TABLE " + nametable + " ADD "+strV+ " varchar(100) NOT NULL");
                        System.out.println(strV);
                    }
                    textField3.setText("");
                    updateQuery("ALTER TABLE " +nametable+ " ADD SummaBallovzaLaboratornue varchar(100) NOT NULL");
                    System.out.println(kolichestvo);

                }

                setVisible(false);

            }
        });
    }
}
