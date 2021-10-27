package modele;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class OutOfDateFood {
    private Image OutOfDateFoodImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../resources/out_of_date_food.png"));
    private int X_Localisation = 0;
    private int Y_Localisation = 0;

    public OutOfDateFood(int initialWidth, int initialHeight) {
        this.FoodImageSize(initialWidth, initialHeight);
    }

    public void FoodImageSize(int width, int height) {
        this.OutOfDateFoodImage = this.OutOfDateFoodImage.getScaledInstance(width, height, 4);
    }

    public Image getFoodImage() {
        return this.OutOfDateFoodImage;
    }

    public void setX(int x) {
        this.X_Localisation = x;
    }

    public int getX() {
        return this.X_Localisation;
    }

    public void setY(int y) {
        this.Y_Localisation = y;
    }

    public int getY() {
        return this.Y_Localisation;
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.X_Localisation, this.Y_Localisation, this.OutOfDateFoodImage.getWidth(null), this.OutOfDateFoodImage.getHeight(null));
    }
}
