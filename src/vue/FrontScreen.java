package vue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.JPanel;
import modele.Bird;
import modele.FreshFood;
import modele.OutOfDateFood;

public class FrontScreen extends JPanel {
    private final int screenWidth;
    private final int screenHeight;
    public static ArrayList<FreshFood> fesh_food_list;
    public static ArrayList<OutOfDateFood> Out_of_date_food_list;
    public static ArrayList<Bird> Bird_list;

    public FrontScreen(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroungImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../resources/fond.jpeg"));
        g.drawImage(backgroungImage, 0, 0, null);

        int i;
        for(i = 0; i < Bird_list.size(); ++i) {
            Bird bird = Bird_list.get(i);
            g.drawImage(bird.getBirdImage(), bird.getX(), bird.getY(), null);
        }

        for(i = 0; i < fesh_food_list.size(); ++i) {
            FreshFood freshFood = fesh_food_list.get(i);
            g.drawImage(freshFood.getFoodImage(), freshFood.getX(), freshFood.getY(), null);
        }

        for(i = 0; i < Out_of_date_food_list.size(); ++i) {
            OutOfDateFood outofdatefood = Out_of_date_food_list.get(i);
            g.drawImage(outofdatefood.getFoodImage(), outofdatefood.getX(), outofdatefood.getY(), null);
        }

    }

    public void setBird(ArrayList<Bird> Bird_list) {
        FrontScreen.Bird_list = Bird_list;
    }

    public void setFreshFood(ArrayList<FreshFood> fesh_food_list) {
        FrontScreen.fesh_food_list = fesh_food_list;
    }

    public void setOutOfDateFood(ArrayList<OutOfDateFood> Out_of_date_food_list) {
        FrontScreen.Out_of_date_food_list = Out_of_date_food_list;
    }
}
