import java.awt.*;
public class Rock {
    public String name;
    public double xPos;
    public double yPos;
    public double dx= (Math.random()*5);
    public double dy= (Math.random()*5);
    public int height = 100;
    public int width = 100;
    public Rectangle rec;

    public Rock(String paramName, int paramXPos, int paramYPos){
        name = paramName;
        xPos = paramXPos;
        yPos = paramYPos;
        rec = new Rectangle((int)xPos, (int)yPos, width, height);
    }

    public void wrap(){
        xPos = xPos + dx;
        yPos = yPos + dy;
        if(yPos < 0-height){
            yPos = 700 + height;
        }
        if(yPos > 800){
            yPos = 0 - height;
        }
        if(xPos > 1000 && dx > 0){
            xPos = 0 - width;
        }
        if(xPos < 0 - width && dx <0){
            xPos = 1000;
        }
        rec = new Rectangle((int)xPos, (int)yPos, width, height);

    }

}