package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{



    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        setDefaulValues();
        getPlayerImage();
    }

    public void getPlayerImage()
    {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/Player Up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Player down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Player down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/Player down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Player Left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/Player Left2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/Player Right1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/Player Right2.png"));


        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDefaulValues () {
        x = 100;
        y = 100;
        speed = 4;
        direction="down";
    }
    public void update(){
        if( keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
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

            g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
        }
}