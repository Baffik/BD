package MySql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MySql.DBConnection.updateQuery;

/**
 * Created by Nika on 12.03.2017.
 */
public class DeletePredmet extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton удалитьButton;


    public DeletePredmet()
    {
        panel1.setBackground(Color.YELLOW);
        setContentPane(panel1);
        setPreferredSize(new Dimension(500/3,600/3));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        pack();
        setAlwaysOnTop( true );
        setLocationByPlatform( true );
        setVisible(true);
        textField1.setText("");

        удалитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField1.getText();
                updateQuery("DELETE from PR WHERE predmet_PR = '" + s +"';");
                updateQuery("DROP TABLE " + s +";");
                setVisible(false);
                System.out.println ("Запись удаленна");

            }
        });
    }
}