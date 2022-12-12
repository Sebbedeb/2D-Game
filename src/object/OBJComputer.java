package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJComputer extends SuperObject {
    GamePanel gp;
    public OBJComputer(GamePanel gp) {
        name = "computer";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Computer.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
