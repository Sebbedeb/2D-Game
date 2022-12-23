package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Jesper extends Entity
{
    public NPC_Jesper(GamePanel gp)
    {
        super(gp);

        setDefault();

        getImage();

    }
    public void setDefault()
    {
        direction = "down";
        speed = 2;
    }

    public void getImage()
    {
        up1 = setup("/NPC/JesperFrontSmoke1");
        up2 = setup("/NPC/JesperFrontSmoke1");
        down1 = setup("/NPC/JesperFrontSmoke1");
        down2 = setup("/NPC/JesperFrontSmoke1");
        left1 = setup("/NPC/JesperFrontSmoke1");
        left2= setup("/NPC/JesperFrontSmoke1");
        right1= setup("/NPC/JesperFrontSmoke1");
        right2= setup("/NPC/JesperFrontSmoke1");
    }

    public void setAction()
    {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //Pick a random number from 1 to 100
            if (i <= 25)
            {
                direction = "up";
            }
            if (i > 25 && i <= 50)
            {
                direction = "down";
            }
            if (i > 50 && i <= 75)
            {
                direction = "left";
            }
            if (i > 75 && i <= 100)
            {
                direction = "right";
            }
    }
}
