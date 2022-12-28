package main;

public class EventHandler
{
    GamePanel gp;
    EventRect[][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent;
    public EventHandler(GamePanel gp)
    {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow)
        {
            eventRect[col][row] = new EventRect();

            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;

            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol)
            {
                col=0;
                row++;
            }
        }

    }

    public void checkEvent()
    {
       //CHECK IF PLAYER IS MORE THAN 1 TILE AWAY FROM LAST EVENT
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if( distance > gp.tileSize)
        {
            canTouchEvent = true;
        }
        if(canTouchEvent)
        {
            if (hit(30, 19, "right"))
            {
                damagePit(30, 19, gp.dialogueState);
            }

            if (hit(32, 16, "up"))
            {
                healingPool(32, 16, gp.dialogueState);
            }
        }
    }

    private void healingPool(int col , int row , int gameState)
    {
        if(gp.keyH.ePressed)
        {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You dip your feet in the \ncold water.\nYour health is restored.";
            gp.player.life = gp.player.maxLife;
        }
    }

    private void damagePit(int col , int row , int gameState)
    {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "An ant bit your ankle, ouch!";
        gp.player.life--;
        canTouchEvent=false;
    }

    public boolean hit(int col, int row, String reqDirection)
    {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidAreaDefaultY;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects( eventRect[col][row]))
        {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any"))
            {
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x =  eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y =  eventRect[col][row].eventRectDefaultY;

        return hit;
    }
}
