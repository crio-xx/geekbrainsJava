package crio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windowGreeting extends JFrame
{
    public windowGreeting()
    {
        //Параметры окна
        setSize(400, 400);
        setLocationRelativeTo(null);


        //Шапка
        JLabel head = new JLabel("Настройки игры.", JLabel.CENTER);
        add(head, BorderLayout.NORTH);

        //Раздел с настройкими
        GridLayout layoutSettings = new GridLayout(5,1);
        JPanel settingsPanel = new JPanel(layoutSettings);


        //Разделитель
        settingsPanel.add(new JLabel(" "));

        //Размер поля
        GridLayout layoutSlider = new GridLayout(2,1);
        JPanel sliderPanel = new JPanel(layoutSlider);

        JLabel statusLabelSize = new JLabel("Размер поля: 3", JLabel.CENTER);

        JSlider fieldSize = new JSlider(2,9,3);
        fieldSize.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                statusLabelSize.setText("Размер поля: " + ((JSlider)e.getSource()).getValue());
            }
            });

        sliderPanel.add(fieldSize);
        sliderPanel.add(statusLabelSize);
        sliderPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        settingsPanel.add(sliderPanel);
        settingsPanel.add(new JLabel(" "));

        //Количество фишек до победы
        GridLayout layoutSpinner = new GridLayout(1,2);
        JPanel spinnerPanel = new JPanel(layoutSpinner);


        JLabel statusLabelDot = new JLabel("Количество фишек до победы:", JLabel.CENTER);
        SpinnerModel spinnerModel = new SpinnerNumberModel(3,
                2,
                9,
                1);
        JSpinner spinner = new JSpinner(spinnerModel);

        spinnerPanel.add(statusLabelDot);
        spinnerPanel.add(spinner);
        spinnerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        settingsPanel.add(spinnerPanel);

        //Разделитель
        settingsPanel.add(new JLabel(" "));
        add(settingsPanel,BorderLayout.CENTER);

        //Footer
        GridLayout layoutBtn = new GridLayout(1,2);
        JPanel btnPanel = new JPanel(layoutBtn);

        JButton exit = new JButton("Выход");
        btnPanel.add(exit, BorderLayout.SOUTH);

        ActionListener exitListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        };
        exit.addActionListener(exitListener);


        JButton restart = new JButton("Играть");
        ActionListener playListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Main.SIZE = Integer.parseInt(String.valueOf(fieldSize.getValue()));
                Main.DOT_WIN = Integer.parseInt(String.valueOf(spinner.getValue()));
                if(Main.DOT_WIN>Main.SIZE)
                {
                    new windowMessage("Ошибка. Количество фишек до победы не может быть больше размера поля.");
                }else
                {
                    Main.initMap(Main.SIZE);
                    Main.window = new windowField(Main.SIZE);
                    setVisible(false);
                    dispose();
                }

            }
        };
        restart.addActionListener(playListener);


        btnPanel.add(restart,BorderLayout.NORTH);

        add(btnPanel,BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
