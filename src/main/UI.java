package main;

import object.OBJTicket;
import object.OBJTicket;

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
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";


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
//        OBJTicket ticket = new OBJTicket(gp);
//        tempImage = ticket.image;

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
            //DO PLAY STATE STUFF
        }

        //PAUSESTATE
        if(gp.gameState == gp.pauseState)
        {
            //PAUSE STUFF
            drawPauseScreen();
        }

        //DIALOGUESTATE
        if(gp.gameState == gp.dialogueState)
        {
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
        y = y1 + gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x , y , gp.tileSize*2 , gp.tileSize*2 , null);
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
