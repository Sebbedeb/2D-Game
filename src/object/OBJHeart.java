package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJHeart extends SuperObject
{
    GamePanel gp;
    public OBJHeart(GamePanel gp) {
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/fullHeart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/halfHeart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/emptyHeart.png"));
            image = uTool.scaleImage(image,gp.tileSize,gp.tileSize);
            image2 = uTool.scaleImage(image2,gp.tileSize,gp.tileSize);
            image3 = uTool.scaleImage(image3,gp.tileSize,gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}