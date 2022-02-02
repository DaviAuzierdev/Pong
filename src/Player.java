import java.awt.*;

public class Player extends Rectangle {
    int x;
    int y;
    int width, height;
    boolean up, down;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 40;
    }

    public void tick() {
        if (up)
            y--;
        else if (down)
            y++;
        if (y + height > (Pong.HEIGHT) ) {
            y = Pong.HEIGHT - height;
        }
        if(y < 0){
           y = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);

    }


}
