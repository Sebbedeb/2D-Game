package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJRAM extends SuperObject
{
    GamePanel gp;
    public OBJRAM(GamePanel gp)
    {
        name = "RAM";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Temp.png"));
            uTool.scaleImage(image , gp.tileSize , gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
