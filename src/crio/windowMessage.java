package crio;

import javax.swing.*;
import java.awt.*;

public class windowMessage extends JFrame
{

    windowMessage(String message)
    {
        setSize(500, 100);
        setLocationRelativeTo(null);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        setVisible(true);
    }

}
