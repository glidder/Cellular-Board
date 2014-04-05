package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Landing area where rocket will land.
 * 
 * @author www.gametutorial.net
 */

public class LandingArea {
    
    /**
     * X coordinate of the landing area.
     */
    public int x;
    /**
     * Y coordinate of the landing area.
     */
    public int y;
    
    /**
     * Image of landing area.
     */
    private BufferedImage landingAreaImg;
    
    /**
     * Width of landing area.
     */
    public int landingAreaImgWidth;
    
    
    public LandingArea()
    {
        Initialize();
        LoadContent();
    }
    
    
    private void Initialize()
    {   
        // X coordinate of the landing area is at 46% frame width.
        x = (int)(Framework.frameWidth * 0.46);
        // Y coordinate of the landing area is at 86% frame height.
        y = (int)(Framework.frameHeight * 0.88);
    }
    
    private void LoadContent()
    {
        try
        {
            URL landingAreaImgUrl = this.getClass().getResource("/moon_lander/resources/images/landing_area.png");
            landingAreaImg = ImageIO.read(landingAreaImgUrl);
            landingAreaImgWidth = landingAreaImg.getWidth();
        }
        catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(landingAreaImg, x, y, null);
    }
    
}
