package object;

//PLACEHOLDER

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJTemp extends SuperObject{
    public OBJTemp()
    {
        name = "temp";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Temp.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
