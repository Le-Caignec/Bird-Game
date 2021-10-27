package controler;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import modele.Bird;
import modele.FreshFood;
import modele.OutOfDateFood;
import vue.FrontScreen;

public class Controller extends MouseAdapter {
    public static final int BIRD_WIDTH = 120;
    public static final int BIRD_HEIGHT = 75;
    public static final int FRESH_FOOD_WIDTH = 120;
    public static final int FRESH_FOOD_HEIGHT = 75;
    public static final int OUT_OF_DATE_FOOD_WIDTH = 120;
    public static final int OUT_OF_DATE_FOOD_HEIGHT = 75;
    public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final JFrame f = new JFrame("Bird Game");
    public JPanel panel;
    private static final Controller C = new Controller();
    private static FrontScreen pgs;
    public static ArrayList<FreshFood> fesh_food_list = new ArrayList();
    public static ArrayList<OutOfDateFood> Out_of_date_food_list = new ArrayList();
    public static ArrayList<Bird> Bird_list = new ArrayList();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller.C.buildFrame();
                Thread t = new Thread() {
                    public void run() {
                        Controller.C.gameScreen();
                    }
                };
                t.start();
            }
        });
    }

    public void buildFrame() {
        this.panel = this.createContentPane();
        this.f.setContentPane(this.panel);
        this.f.getContentPane().addMouseListener(this);
        this.f.setResizable(true);
        this.f.setDefaultCloseOperation(3);
        this.f.setAlwaysOnTop(false);
        this.f.setVisible(true);
        this.f.setMinimumSize(new Dimension(SCREEN_WIDTH * 1 / 4, SCREEN_HEIGHT * 1 / 4));
        this.f.setExtendedState(6);
    }

    public JPanel createContentPane() {
        this.panel = new JPanel();
        LayoutManager overlay = new OverlayLayout(this.panel);
        this.panel.setLayout(overlay);
        pgs = new FrontScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.panel.add(pgs);
        this.panel.revalidate();
        this.panel.repaint();
        return this.panel;
    }

    private void gameScreen() {
        this.popup_bird();
        pgs.setBird(Bird_list);
        pgs.setFreshFood(fesh_food_list);
        pgs.setOutOfDateFood(Out_of_date_food_list);
    }

    public void mouseClicked(MouseEvent e) {
        PrintStream var10000 = System.out;
        int var10001 = e.getX();
        var10000.println(var10001 + " : " + e.getY());
        double a = Math.random();
        if (a >= 0.3D) {
            this.popup_fresh_food(e.getX(), e.getY());
        } else {
            this.popup_out_of_date_food(e.getX(), e.getY());
        }

        this.panel.revalidate();
        this.panel.update(pgs.getGraphics());
    }

    public void popup_bird() {
        Bird bird = new Bird(120, 75);
        bird.setX(SCREEN_WIDTH / 2);
        bird.setY(SCREEN_HEIGHT / 2);
        Bird_list.add(bird);
    }

    public void popup_fresh_food(int X, int Y) {
        FreshFood freshFood = new FreshFood(120, 75);
        freshFood.setX(X);
        freshFood.setY(Y);
        fesh_food_list.add(freshFood);
    }

    public void popup_out_of_date_food(int X, int Y) {
        OutOfDateFood outofdatefood = new OutOfDateFood(120, 75);
        outofdatefood.setX(X);
        outofdatefood.setY(Y);
        Out_of_date_food_list.add(outofdatefood);
    }
}
