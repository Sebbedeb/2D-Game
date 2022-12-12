package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager
{
    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];

        mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();

        loadMap("/maps/WorldMap1.txt");
    }

    public void getTileImage() {

            setup(0, "Dungeon Floor", false);
            setup(1, "Dungeon Floor 2", false);
            setup(2, "Dungeon Wall", true);
            setup(3, "Dungeon Door", false);
            setup(4, "Dirt", false);
            setup(5, "Sand", false);
            setup(6, "Tree", true);
            setup(7, "Grass", false);
            setup(8, "Water", true);
            setup(9, "Path", false);
    }

    public void setup(int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try{
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+ ".png"));
            tiles[index].image = uTool.scaleImage(tiles[index].image , gp.tileSize , gp.tileSize);
            tiles[index].collision = collision;

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        //2-Dimensional Array method
        int worldCol = 0;
        int worldRow = 0;


        //while loop drawing tiles
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {

            int tileNum = mapTileNum[worldCol][worldRow];

            //eg. world X = 0 * 16 ,, 1*16 ,, 2*16 ...
            int worldX = worldCol * gp.tileSize;
            //eg. world X = 0 * 16 ,, 1*16 ,, 2*16 ...
            int worldY = worldRow * gp.tileSize;
            //Offset camera, so player is in the middle
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //only draw the tiles we see + 1
                 if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


        /* // Home made method with for loops
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

        int counter = 0;
        for(int i = gp.tileSize ; i < (gp.maxScreenRow * gp.tileSize) - gp.tileSize ; i += gp.tileSize) {

            for (int j = gp.tileSize; j < ( gp.tileSize * gp.maxScreenColumn )- gp.tileSize; j += gp.tileSize) {
                if( counter % 2 == 0)
                {
                    g2.drawImage(tiles[0].image, j, i, gp.tileSize, gp.tileSize, null);
                }
                if(counter % 2 != 0)
                {
                    g2.drawImage(tiles[1].image, j , i, gp.tileSize, gp.tileSize, null);
                }
                counter++;
            }
            counter++;
        }
    }

         */

    }
}
