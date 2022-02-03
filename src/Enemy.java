import java.awt.*;

public class Enemy extends Player {

  public Enemy(int x, int y) {
    super(x, y);
    super.width = 10;
    super.height = 40;

  }
  public void tick(){
    y+=(Pong.ball.y - y - 6) *0.07;
  }
  public void render(Graphics graphics){
    graphics.setColor(Color.white);
    graphics.drawRect(x,y,width, height);
  }

}
