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
/**
 * 
 * 
 */
public class Swing extends JFrame {
    
    private final int FRAME_WIDTH = 1024;
    private final int FRAME_HEIGHT = 1024;
    private final String FRAME_TITLE = "Shape Rotation";
    private final int NUMBER_OF_COLORS = 10;
    private final String BG_COLOR = "BG Colors";
    private final String FG_COLOR = "FG Colors";

    private final List<Color> bgPalette = new ArrayList<>();
    private final List<Color> fgPalette = new ArrayList<>();
    private final SwingPanel panel;
    private final Random rng = new Random();
/**
 * The swing constructor.
 */
    public Swing() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        this.panel = new SwingPanel();
        pane.add(panel);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            Color color = makeColor(64, 128);
            bgPalette.add(color);
        } // for
        this.panel.setBackground(bgPalette.get(0));

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            Color color = makeColor(32, 100);
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
            makeMenuItem( BG_COLOR, i, bgListener, bgColorMenu );
        } // for

        JMenu fgColorMenu = new JMenu(FG_COLOR);
        menuBar.add(fgColorMenu);

        MenuListener fgListener = new MenuListener(MenuListener.FG_MODE,
                this.FG_COLOR, this.fgPalette, this.panel);

        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            makeMenuItem( FG_COLOR, i, fgListener, fgColorMenu );
        } // for

        this.setVisible(true);
    } // Swing()
/**
 * Generate colors for both foreground and background.
 * 
 * @param lo the minimum value of a given color
 * @param hi the maximum value of a given color
 * @return a set of 3 values representing an RGB color
 */
    public final Color makeColor(int lo, int hi) {
        int red = lo + this.rng.nextInt(hi);
        int green = lo + this.rng.nextInt(hi);
        int blue = 100 + this.rng.nextInt(hi);
        Color color = new Color(red, green, blue);
        return color;
    } // makeColor( int, int )
/**
 * 
 */
    public final void makeMenuItem( String prefix, int index,
            MenuListener listener, JMenu menu ) {
        String label = prefix + " " + index;
        JMenuItem item = new JMenuItem(label);
        item.addActionListener(listener);
        item.setActionCommand(label);
        menu.add(item);
    } // makeMenuItem()

    public static void main(String[] args) {
        Swing swing = new Swing();
    } // main( String [] )

} // Swing
