package crio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windowField extends JFrame
{
    private static final String pathXpng = "images/scaledX.png";
    private static final String pathOpng = "images/scaledO.png";
    public static JButton[][] cells;

    public windowField(int size)
    {

        setSize(500, 500);
        setLocationRelativeTo(null);

        GridLayout layout = new GridLayout(size, size);

        JPanel btnPanel = new JPanel(layout);

        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JButton btn = (JButton) e.getSource();
                changeIcon(btn,1);
                int point = Integer.parseInt(btn.getName());
                int x = point/size;
                int y = point%size;
                Main.makeMove(1,x,y);
                Main.answerAi();
            }
        };


        cells = new JButton[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                cells[i][j] = new JButton();
                cells[i][j].addActionListener(actionListener);
                cells[i][j].setName((i*size+j)+"");
                btnPanel.add(cells[i][j]);
            }
        }


        add(btnPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void changeIcon(JButton btn, int player)
    {
        if(player==1)
        {
            btn.setIcon(new ImageIcon(pathXpng));
        }else
        {
            btn.setIcon(new ImageIcon(pathOpng));
        }
        btn.setEnabled(false);
    }

    static void disableField(JButton[][] cells)
    {
        for (int i = 0; i < Main.SIZE; i++)
        {
            for (int j = 0; j < Main.SIZE; j++)
            {
                cells[i][j].setEnabled(false);
            }
        }
    }

}
