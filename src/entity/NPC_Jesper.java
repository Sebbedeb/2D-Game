package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Jesper extends Entity
{
    BufferedImage smoke1, smoke2;

    public NPC_Jesper(GamePanel gp)
    {
        super(gp);

        setDefault();

        getImage();

        setDialogue();

    }

    public void setDefault()
    {
        direction = "down";
        speed = 1;
    }

    public void getImage()
    {
        up1 = setup("/NPC/JesperBackWalk1");
        up2 = setup("/NPC/JesperBackWalk2");
        down1 = setup("/NPC/JesperFrontWalk1");
        down2 = setup("/NPC/JesperFrontWalk2");
        left1 = setup("/NPC/JesperLeftWalk1");
        left2 = setup("/NPC/JesperLeftWalk2");
        right1 = setup("/NPC/JesperRightWalk1");
        right2 = setup("/NPC/JesperRightWalk2");
        idle1 = setup("/NPC/JesperFrontSmoke1");
        idle2 = setup("/NPC/JesperFrontSmoke1");

    }

    public void setDialogue()
    {
        dialogues[0] = "2 sec, ryger lige.";
        dialogues[1] = "C# er bare java med stort \nforbogstav i funktionerne. \nEZ.";
        dialogues[2] = "Vi burde få et halvtag til \nrygerne.";
        dialogues[3] = "*Jesper headbanger*.";
    }

    public void setAction()
    {
        actionLockCounter++;
        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //Pick a random number from 1 to 100
            if (i <= 25)
            {
                direction = "up";
            }
            if (i > 20 && i <= 40)
            {
                direction = "down";
            }
            if (i > 40 && i <= 60)
            {
                direction = "left";
            }
            if (i > 60 && i <= 80)
            {
                direction = "right";
            }
            if (i > 80 && i <= 100)
            {
                direction = "idle";
            }
            actionLockCounter=0;
        }
    }
    public void speak()
    {
        super.speak();
    }
}