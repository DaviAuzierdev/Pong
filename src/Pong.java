import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Pong extends Canvas implements Runnable, KeyListener {
    static JFrame frame;
    public boolean isRunning;
    public Thread thread;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 160;
    public static final int SCALE = 3;
    public BufferedImage image;
    Player player;
    public Pong(){
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        player = new Player(0,(HEIGHT/2) - 40);
    }
    public synchronized void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }
    public synchronized void stop(){
        isRunning = false;
        try{
            thread.join();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public static void main(String[] args){
        Pong p = new Pong();
        frame = new JFrame();
        frame.setTitle("Pong");
        frame.add(p);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        p.start();
    }
    public void tick(){
        player.tick();
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
           return;
        }
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH, HEIGHT);
        player.render(g);


        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0,0, WIDTH*SCALE,HEIGHT*SCALE,null);

        bs.show();
    }

    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfticks = 60.0;
        double ns = 1000000000/amountOfticks;
        double delta = 0;
        int framesPerSecond = 0;
        double timer = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            delta+=(now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1 ){
                tick();
                render();
                framesPerSecond++;
                delta--;
            }
            if(System.currentTimeMillis() - timer > 1000){
                System.out.println("FPS: "+framesPerSecond);
                framesPerSecond = 0;
                timer+=1000;
            }
        }



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = false;
        }
    }
}
