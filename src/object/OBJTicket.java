package object;

//PLACEHOLDER

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJTicket extends SuperObject{
    GamePanel gp;
    public OBJTicket(GamePanel gp)
    {
        name = "ticket";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ticket.png"));
            uTool.scaleImage(image , gp.tileSize , gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
