package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJSign extends SuperObject{

    public OBJSign()
    {
        name= "Sign";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Sign.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
