import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.Random;

public class Ball {
    double speed = 1.4;
    double x,y;
    int width;
    int height;

    double dx;
    double dy;
    public Ball(int x, int y) {
       this.x = x;
       this.y = y;
        this.width = 10;
        this.height = 10;
        int angle = new Random().nextInt((((120+90) - 45) +45));
        this.dx = dx = Math.cos(Math.toRadians(angle));
        this.dy = dy = Math.sin(Math.toRadians(angle));


    }
    public void tick(){
        x+=dx*speed;
        y+=dy*speed;
        if(y+(dy*speed) + height > Pong.HEIGHT){
            dy*=-1;
        }else if(y+(dy*speed) < 0){
            dy*=-1;
        }
        Rectangle limite = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
        Rectangle limitePlayer = new Rectangle(Pong.player.x, Pong.player.y, Pong.player.width, Pong.player.height);
        Rectangle limiteEnemy = new Rectangle((int)Pong.enemy.x, (int)Pong.enemy.y, Pong.enemy.width, Pong.enemy.height);

        if(limite.intersects(limitePlayer)){
            dx*=-1;
        }
        else if(limite.intersects(limiteEnemy)){
            dx*=-1;
        }

    }
    public void render(Graphics graphics){
        graphics.setColor(Color.white);
        graphics.fillOval((int)x,(int)y,width, height);
    }
}
