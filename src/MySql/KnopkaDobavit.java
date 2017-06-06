package MySql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MySql.DBConnection.updateQuery;


/**
 * Created by Nika on 07.03.2017.
 */
public class KnopkaDobavit extends JFrame {
    private JTextField textField1;
    private JButton внестиДанныеButton;
    private JPanel panel;


    public KnopkaDobavit()
    {
        panel.setBackground(Color.YELLOW);
        setContentPane(panel);
        setPreferredSize(new Dimension(500/3,600/3));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        pack();
        setVisible(true);
       //setAlwaysOnTop( true );
        //setLocationByPlatform( true );


        внестиДанныеButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = textField1.getText();
                updateQuery("INSERT INTO PR(predmet_PR)VALUES('"+s+"')");
                textField1.setText("");
                setVisible(false);

            }
        });
    }


}
