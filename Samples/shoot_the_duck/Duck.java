package shoot_the_duck;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The duck class.
 * 
 * @author www.gametutorial.net
 */

public class Duck {
    
    /**
     * How much time must pass in order to create a new duck?
     */
    public static long timeBetweenDucks = Framework.secInNanosec / 2;
    /**
     * Last time when the duck was created.
     */
    public static long lastDuckTime = 0;
    
    /**
     * Duck lines.
     * Where is starting location for the duck?
     * Speed of the duck?
     * How many points is a duck worth?
     */
    public static int[][] duckLines = {
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.60), -2, 20},
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.65), -3, 30},
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.70), -4, 40},
                                       {Framework.frameWidth, (int)(Framework.frameHeight * 0.78), -5, 50}
                                      };
    /**
     * Indicate which is next duck line.
     */
    public static int nextDuckLines = 0;
    
    
    /**
     * X coordinate of the duck.
     */
    public int x;
    /**
     * Y coordinate of the duck.
     */
    public int y;
    
    /**
     * How fast the duck should move? And to which direction?
     */
    private int speed;
    
    /**
     * How many points this duck is worth?
     */
    public int score;
    
    /**
     * Duck image.
     */
    private BufferedImage duckImg;
    
    
    /**
     * Creates new duck.
     * 
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param speed The speed of this duck.
     * @param score How many points this duck is worth?
     * @param duckImg Image of the duck.
     */
    public Duck(int x, int y, int speed, int score, BufferedImage duckImg)
    {
        this.x = x;
        this.y = y;
        
        this.speed = speed;
        
        this.score = score;
        
        this.duckImg = duckImg;        
    }
    
    
    /**
     * Move the duck.
     */
    public void Update()
    {
        x += speed;
    }
    
    /**
     * Draw the duck to the screen.
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(duckImg, x, y, null);
    }
}
