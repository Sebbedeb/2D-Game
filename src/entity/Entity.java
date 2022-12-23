package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity
{
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, right1, right2, idle1, idle2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public boolean moving;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setAction() {}
    public void speak(){
        if(dialogues[dialogueIndex] == null)
        {
            dialogueIndex = 0;
        }
        switch(gp.player.direction)
        {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }
    public void update()
    {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);


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


    public BufferedImage setup(String imagePath)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2)
    {
        {
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //only draw the tiles we see + 1
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                BufferedImage image = null;
                switch (direction)
                {
                    case "up":
                        if (spriteNumber == 1)
                        {
                            image = up1;
                        }
                        if (spriteNumber == 2)
                        {
                            image = up2;
                        }
                        break;

                    case "down":
                        if (spriteNumber == 1)
                        {
                            image = down1;
                        }
                        if (spriteNumber == 2)
                        {
                            image = down2;
                        }
                        break;


                    case "left":
                        if (spriteNumber == 1)
                        {
                            image = left1;
                        }
                        if (spriteNumber == 2)
                        {
                            image = left2;
                        }
                        break;

                    case "right":
                        if (spriteNumber == 1)
                        {
                            image = right1;
                        }
                        if (spriteNumber == 2)
                        {
                            image = right2;
                        }
                        break;
                    case "idle":
                        if (spriteNumber == 1)
                        {
                            image = idle1;
                        }
                        if (spriteNumber == 2)
                        {
                            image = idle2;
                        }
                        break;
                }
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

        }
    }


}
