package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJSign extends SuperObject{

    GamePanel gp;
    public OBJSign(GamePanel gp)
    {
        name= "sign";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Sign.png"));
            uTool.scaleImage(image , gp.tileSize , gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
