package modele;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Bird {
    private Image BirdImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../resources/bird.png"));
    private int X_Localisation = 0;
    private int Y_Localisation = 0;

    public Bird(int initialWidth, int initialHeight) {
        this.BirdImageSize(initialWidth, initialHeight);
    }

    public void BirdImageSize(int width, int height) {
        this.BirdImage = this.BirdImage.getScaledInstance(width, height, 4);
    }

    public Image getBirdImage() {
        return this.BirdImage;
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
        return new Rectangle(this.X_Localisation, this.Y_Localisation, this.BirdImage.getWidth(null), this.BirdImage.getHeight(null));
    }

}
