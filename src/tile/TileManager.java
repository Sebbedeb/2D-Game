package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tiles = new Tile[10];

        getTileImage();
    }

    public void getTileImage()
    {
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor 2.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Wall.png"));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        for(int i = 0; i < gp.tileSize * gp.maxScreenColumn; i+=gp.tileSize)
        {
            g2.drawImage(tiles[2].image, i, 0, gp.tileSize, gp.tileSize, null);
        }
        for(int i = 0; i < gp.tileSize * gp.maxScreenRow; i += gp.tileSize)
        {
            g2.drawImage(tiles[2].image, 0, i, gp.tileSize, gp.tileSize, null);
        }
        for(int i = 0; i < gp.tileSize * gp.maxScreenRow; i += gp.tileSize)
        {
            g2.drawImage(tiles[2].image, (gp.maxScreenColumn*gp.tileSize)-gp.tileSize, i, gp.tileSize, gp.tileSize, null);
        }
        for(int i = 0; i < gp.tileSize * gp.maxScreenColumn; i += gp.tileSize)
        {
            g2.drawImage(tiles[2].image, i, (gp.maxScreenRow*gp.tileSize)-gp.tileSize, gp.tileSize, gp.tileSize, null);
        }


        for(int i = gp.tileSize ; i < (gp.maxScreenRow * gp.tileSize) - gp.tileSize ; i += gp.tileSize) {
            int counter = 0;

            for (int j = gp.tileSize; j < ( gp.tileSize * gp.maxScreenColumn )- gp.tileSize; j += 2 * gp.tileSize) {
                g2.drawImage(tiles[0].image, j, i, gp.tileSize, gp.tileSize, null);
                g2.drawImage(tiles[1].image, j+gp.tileSize, i, gp.tileSize, gp.tileSize, null);

            }
        }
    }

}
