package view;

import controller.Controller;
import controller.EventListener;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame
{
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener)
    {
        field.setEventListener(eventListener);
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");

        // Menu added here
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(getParent(), "Game SOKOBAN\nAuthor: Andrey Demyanenko\nDec 2016", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem restart = new JMenuItem("Resart");
        restart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.restart();
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem level = new JMenuItem("Select level");
        level.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane pane = new JOptionPane("Enter level number", JOptionPane.QUESTION_MESSAGE);
                pane.setWantsInput(true);
                JDialog dialog = pane.createDialog("Level selector");
                dialog.setVisible(true);
                int level = Integer.parseInt((String)pane.getInputValue());
                controller.startLevel(level);
            }
        });

        menuBar.add(level);
        menuBar.add(restart);
        menuBar.add(about);
        menuBar.add(exit);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(null, level + "Completed",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
