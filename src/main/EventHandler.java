package main;

import java.awt.*;

public class EventHandler
{
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp)
    {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRectDefaultX = gp.tileSize / 2 + 2;
        eventRectDefaultY = gp.tileSize / 2 + 2;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
    }

    public void checkEvent()
    {
        if(hit(30,19,"right"))
        {
            damagePit(gp.dialogueState);
        }

    }

    private void damagePit(int gameState)
    {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "An ant bit your ankle, ouch!";
        gp.player.life--;

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection)
    {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidAreaDefaultY;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect))
        {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any"))
            {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
}
