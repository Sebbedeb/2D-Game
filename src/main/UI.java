package main;

import object.OBJHeart;
import object.OBJTicket;
import object.OBJTicket;
import object.SuperObject;

import java.awt.*;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font nokia;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(GamePanel gp)
    {
        this.gp = gp;

        try
        {
            InputStream is =getClass().getResourceAsStream("/fonts/nokiafc22.ttf");
            nokia = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        //CREATE HUD OBJECTS
        SuperObject heart = new OBJHeart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;

    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(nokia);
        g2.setColor(Color.white);


        //TITLESTATE
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        //PLAYSTATE
        if(gp.gameState == gp.playState)
        {
            drawPlayerLife();
        }

        //PAUSESTATE
        if(gp.gameState == gp.pauseState)
        {
            drawPlayerLife();
            drawPauseScreen();
        }

        //DIALOGUESTATE
        if(gp.gameState == gp.dialogueState)
        {
            drawPlayerLife();
            drawDialogueScreen();
        }

    }

    private void drawTitleScreen()
    {
        //BACKGROUND COLOR
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        //TITLE NAME

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Første \nSemester";
        String[] line = text.split("\n");
        int x = getXForCenteredText(line[0]);
        int y = gp.tileSize * 2;
        int x1 = getXForCenteredText(line[1]);
        int y1= gp.tileSize * 4;
        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(line[0], x+5 , y+5);
        g2.drawString(line[1], x1+5 , y1+5);
        //MAIN
        g2.setColor(Color.white);
        g2.drawString(line[0], x, y);
        g2.drawString(line[1], x1, y1);

        //PLAYER IMAGE
        x = gp.screenWidth/2 - gp.tileSize;
        y = y1 + gp.tileSize;
        g2.drawImage(gp.player.down1, x , y , gp.tileSize*2 , gp.tileSize*2 , null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD , 40F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0)
        {
            g2.drawString("*" ,x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1)
        {
            g2.drawString("*" ,x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2)
        {
            g2.drawString("*" ,x - gp.tileSize, y);
        }
    }

    public void drawPlayerLife()
    {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        while ( i < gp.player.maxLife/2)
        {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i < gp.player.life)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life)
            {
                g2.drawImage(heart_full,x , y , null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 80));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen()
    {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 26));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y+=40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x , y , width , height , 35 , 35);

        c = new Color(255,255,255);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(c);
        g2.drawRoundRect(x + 5 ,y + 5 , width - 10 , height - 10 , 25 , 25);
    }
    public int getXForCenteredText(String text)
    {
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        return x;
    }
}
