package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];

        mapTileNum = new int [gp.maxScreenColumn] [gp.maxScreenRow];

        getTileImage();

        loadMap("/Maps/Mapx1.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Floor 2.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Wall.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dungeon Door.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png"));
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenColumn && row < gp.maxScreenRow)
            {
                String line = br.readLine();

                while(col < gp.maxScreenColumn)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenColumn)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e)
        {
        }
    }
    public void draw(Graphics2D g2) {
        //2-Dimensional Array method
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenColumn && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenColumn) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
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
