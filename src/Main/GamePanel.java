package Main;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16*16 pixel tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 pixel tile

    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        // DELTA METHOD
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while(gameThread != null)
        {

            //Check system time
            currentTime = System.nanoTime();

            //How much time has passed / drawInterval (0.016)
            delta += (currentTime - lastTime) / drawInterval;
            //set the timer to the change from last time to current time
            timer += (currentTime - lastTime);

            //lastTime becomes currentTime
            lastTime = currentTime;

            //When delta = drawInterval we update and repaint , then reset delta
            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000)
            {
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }







        /* METHOD 1 : MEASURE VIA SYSTEM TIME
        //We measure time in nanoseconds for accuracy (overkill)
        //1 second is 1b nanoseconds
        //we divide 1b (1 nanosecond) with our FPS = we update every 0.016 sec
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null)
        {
            // 1 UPDATE: update information such as character position
            update();

            // 2 DRAW:  draw the screen with the updated
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                //Since Thread.sleep takes milliseconds, we convert from nano to milli
                remainingTime = remainingTime/1000000;
                //Since Thread.sleep takes a long, we convert from double to long
;

                if(remainingTime < 0 ){
                    remainingTime=0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

         */
    }

    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
