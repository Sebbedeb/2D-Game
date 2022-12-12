package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{



    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
//    public int hasTicket;
//    public int jesperQuestComplete = 0;


    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea= new Rectangle();
        solidArea.x = (int) (gp.tileSize*0.4);
        solidArea.y = (int) (gp.tileSize*0.4);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int) (gp.tileSize*0.4);
        solidArea.height = (int) (gp.tileSize*0.6);

        setDefaulValues();
        getPlayerImage();
    }

    public void getPlayerImage()
    {
        up1 = setup("Player Up1");
        up2 = setup("Player Up2");
        down1 = setup("Player down1");
        down2 = setup("Player down2");
        left1 = setup("Player Left1");
        left2= setup("Player Left2");
        right1= setup("Player Right1");
        right2= setup("Player Right2");
    }
    public BufferedImage setup(String imageName)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/player/"+ imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
    public void setDefaulValues () {
        worldX = gp.tileSize * 36;
        worldY = gp.tileSize * 8;
        speed = 4;
        direction="up";
    }
    public void update(){
        if( keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if (keyH.upPressed)
            {
                direction = "up";

            } else if (keyH.downPressed)
            {
                direction = "down";

            } else if (keyH.leftPressed)
            {
                direction = "left";

            } else if (keyH.rightPressed)
            {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objectIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false)
            {
                switch (direction)
                {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12)
            {
                if (spriteNumber == 1)
                {
                    spriteNumber = 2;
                } else if (spriteNumber == 2)
                {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {
        }

    }
    public void draw(Graphics2D g2)
        {
            BufferedImage image = null;
            switch (direction)
            {
                case "up":
                    if(spriteNumber == 1) {
                        image = up1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = up2;
                    }
                    break;

                case "down":
                    if(spriteNumber == 1) {
                        image = down1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = down2;
                    }
                    break;


                case "left":
                    if(spriteNumber == 1) {
                        image = left1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = left2;
                    }
                    break;

                case "right":
                    if(spriteNumber == 1) {
                        image = right1;
                    }
                    if(spriteNumber == 2)
                    {
                        image = right2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, null);
        }
}