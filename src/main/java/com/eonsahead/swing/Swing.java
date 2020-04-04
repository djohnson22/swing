package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Swing extends JFrame {

    private final int FRAME_WIDTH = 512;
    private final int FRAME_HEIGHT = 512;
    private final String FRAME_TITLE = "Swing";
    private final int NUMBER_OF_COLORS = 8;
    private final String BG_COLOR = "Background Color";
    private final String FG_COLOR = "Foreground Color";

    private final List<Color> bgPalette = new ArrayList<>();
    private final List<Color> fgPalette = new ArrayList<>();
    private final SwingPanel panel;

    public Swing() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        this.panel = new SwingPanel();
        pane.add(panel);

        Random rng = new Random();
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            int red = 64 + rng.nextInt(128);
            int green = 64 + rng.nextInt(128);
            int blue = 64 + rng.nextInt(128);
            Color color = new Color(red, green, blue);
            bgPalette.add(color);
        } // for
        this.panel.setBackground(bgPalette.get(0));

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            int red = 32 + rng.nextInt(224);
            int green = 32 + rng.nextInt(224);
            int blue = 32 + rng.nextInt(224);
            Color color = new Color(red, green, blue);
            fgPalette.add(color);
        } // for
        this.panel.setColor(fgPalette.get(0));

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu bgColorMenu = new JMenu(BG_COLOR);
        menuBar.add(bgColorMenu);

        MenuListener bgListener = new MenuListener(MenuListener.BG_MODE,
                this.BG_COLOR, this.bgPalette, this.panel);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            String label = BG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(bgListener);
            item.setActionCommand(label);
            bgColorMenu.add(item);
        } // for

        JMenu fgColorMenu = new JMenu(FG_COLOR);
        menuBar.add(fgColorMenu);

        MenuListener fgListener = new MenuListener(MenuListener.FG_MODE,
                this.FG_COLOR, this.fgPalette, this.panel);
        
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            String label = FG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(fgListener);
            item.setActionCommand(label);
            fgColorMenu.add(item);
        } // for

        this.setVisible(true);
    } // Swing()

    public static void main(String[] args) {
        Swing swing = new Swing();
    } // main( String [] )

} // Swing
