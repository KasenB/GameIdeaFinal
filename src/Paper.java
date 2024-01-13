import java.awt.*;
public class Paper {
    public String name;
    public double xPos;
    public double yPos;
    public double dx= (Math.random()*5);
    public double dy= (Math.random()*5);
    public int height = 100;
    public int width = 100;
    public Rectangle rec;

    public Paper(String paramName, int paramXPos, int paramYPos){
        name = paramName;
        xPos = paramXPos;
        yPos = paramYPos;
        rec = new Rectangle((int)xPos, (int)yPos, width, height);
    }

    public void move(){
        xPos = xPos + dx;
        yPos = yPos + dy;
        // || = or
        if(yPos < 0 || yPos > 700-height){
            dy = -dy;
        } // top/bottom

        if(xPos > 900 || xPos < 0){
            dx = -dx;
        } // side/side

        rec = new Rectangle((int)xPos, (int)yPos, width, height);
    }

}
