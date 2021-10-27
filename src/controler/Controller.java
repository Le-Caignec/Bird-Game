package controler;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import modele.Bird;
import modele.FreshFood;
import modele.OutOfDateFood;
import vue.FrontScreen;

public class Controller extends MouseAdapter {
    //Variable static Globale
    public static final int BIRD_WIDTH = 120;
    public static final int BIRD_HEIGHT = 75;
    public static final int FRESH_FOOD_WIDTH = 120;
    public static final int FRESH_FOOD_HEIGHT = 75;
    public static final int OUT_OF_DATE_FOOD_WIDTH = 120;
    public static final int OUT_OF_DATE_FOOD_HEIGHT = 75;
    public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    //Variable Objet
    private final JFrame f = new JFrame("Bird Game");
    public JPanel panel;
    private static final Controller C = new Controller();
    private static FrontScreen pgs;
    public static ArrayList<FreshFood> fesh_food_list = new ArrayList();
    public static ArrayList<OutOfDateFood> Out_of_date_food_list = new ArrayList();
    public static ArrayList<Bird> Bird_list = new ArrayList();
    private static long start = System.currentTimeMillis();;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                C.buildFrame();
                Thread t = new Thread(() -> C.gameScreen());
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
        System.out.println("X position : "+e.getX() + " : " +"Y position : "+e.getY());
        double a = Math.random();
        if (a >= 0.3D) {
            this.popup_fresh_food(e.getX(), e.getY());
        } else {
            this.popup_out_of_date_food(e.getX(), e.getY());
        }

        this.panel.revalidate();
        this.panel.repaint();
    }

    public void popup_bird() {
        Bird bird = new Bird(BIRD_WIDTH, BIRD_HEIGHT);
        bird.setX(SCREEN_WIDTH / 2);
        bird.setY(SCREEN_HEIGHT / 2);
        Bird_list.add(bird);
    }

    public void popup_fresh_food(int X, int Y) {
        double currentTime=(double)(System.currentTimeMillis() - start)/1000;
        for (int i=0;i<fesh_food_list.size();i++){
            if (fesh_food_list.get(i).getTime_leave()>currentTime){

            }
        }
        FreshFood freshFood = new FreshFood(FRESH_FOOD_WIDTH, FRESH_FOOD_HEIGHT);
        freshFood.setX(X);
        freshFood.setY(Y);
        freshFood.setTime_leave((int)(System.currentTimeMillis() - start)/1000);
        fesh_food_list.add(freshFood);
    }

    public void popup_out_of_date_food(int X, int Y) {
        double currentTime=(double)(System.currentTimeMillis() - start)/1000;
        for (int i=0;i<Out_of_date_food_list.size();i++){
            double time_of_food=(Out_of_date_food_list.get(i).getTime_leave()- start)/1000;
            if ( time_of_food > currentTime){

            }
        }
        OutOfDateFood outofdatefood = new OutOfDateFood(OUT_OF_DATE_FOOD_WIDTH, OUT_OF_DATE_FOOD_HEIGHT);
        outofdatefood.setX(X);
        outofdatefood.setY(Y);
        outofdatefood.setTime_leave(currentTime);
        Out_of_date_food_list.add(outofdatefood);
    }
}
