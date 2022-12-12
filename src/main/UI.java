package main;

import object.OBJTicket;
import object.OBJTicket;

import java.awt.*;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.text.DecimalFormat;

public class UI
{

    GamePanel gp;
    Graphics2D g2;
    Font arial_BOLD_40;
    Font arial_BOLD_80;
    Font arial_40;
//    BufferedImage tempImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0,00");


    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_BOLD_40 = new Font("Arial", Font.BOLD, 40);
        arial_BOLD_40 = new Font("Arial", Font.BOLD, 80);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
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
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState)
        {
            //DO PLAY STATE STUFF
        }
        if(gp.gameState == gp.pauseState)
        {
            //PAUSE STUFF
            drawPauseScreen();
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
    public int getXForCenteredText(String text)
    {
        int x;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        return x;
    }
}
