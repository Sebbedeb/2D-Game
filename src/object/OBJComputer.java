package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJComputer extends SuperObject {
    public OBJComputer() {
        name = "computer";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Computer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
