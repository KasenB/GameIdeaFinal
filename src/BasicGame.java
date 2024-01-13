//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

//*******************************************************************************

public class BasicGame implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Object-related variables
    // **** STEP 1: DECLARE ASTRONAUT AND ITS IMAGE
    public Paper paper1;
    public Rock rock1;
    public Scissors scissors1;
    public Image paperPic;
    public Image rockPic;
    public Image scissorsPic;
    public Image libraryPic;
    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGame ex = new BasicGame();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGame() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        // **** STEP 2: CONTSTRUCT ASTRONAUT AND ITS IMAGE
        paper1 = new Paper("Paper1", 200, 300);
        paperPic = Toolkit.getDefaultToolkit().getImage("paper.png");
        rock1 = new Rock("Rock1",800,300);
        rockPic = Toolkit.getDefaultToolkit().getImage("rock.png");
        scissors1 = new Scissors("Scissors1", 500, 300);
        scissorsPic = Toolkit.getDefaultToolkit().getImage("scissors.png");
        libraryPic = Toolkit.getDefaultToolkit().getImage("library.jpeg");
    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
        paper1.move();
        rock1.wrap();
        scissors1.move();

    }

    public void collisions(){
        // check whether oliver and the asteroid are colliding
        // if they collide, reverse directions
        if (paper1.rec.intersects(rock1.rec)) {
            paper1.dx = -paper1.dx * 1.4;
            paper1.dy = -paper1.dy * 1.4;
            rock1.dx = -rock1.dx * 0.6;
            rock1.dy = -rock1.dy * 0.6;
            // when they intersect multiple times, the -dy goes back to pisitive and then subtracts 1, making it slower, even though paper should get slower
        }
        if (rock1.rec.intersects(scissors1.rec)){
            rock1.dx = -rock1.dx * 1.4;
            rock1.dy = -rock1.dy * 1.4;
            scissors1.dx = -scissors1.dx * 0.6;
            scissors1.dy = -scissors1.dy * 0.6;
        }
        if (scissors1.rec.intersects(paper1.rec)){
            scissors1.dx = -scissors1.dx * 1.4;
            scissors1.dy = -scissors1.dy * 1.4;
            paper1.dx = -paper1.dx * 0.6;
            paper1.dy = -paper1.dy * 0.6;
        }
    }
    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images
        // ***** STEP 3: DRAW THE IMAGE TO THE SCREEEN
//        g.drawImage(oliverPic, 500,0, 300,300, null);
        g.drawImage(libraryPic, 0, 0,WIDTH,HEIGHT,null);
        g.drawImage(paperPic, (int)paper1.xPos, (int)paper1.yPos, paper1.width, paper1.height, null);
        g.drawImage(rockPic, (int)rock1.xPos, (int)rock1.yPos, rock1.width, rock1.height, null);
        g.drawImage(scissorsPic, (int)scissors1.xPos, (int)scissors1.yPos, scissors1.width, scissors1.height, null);
        //g.setColor(Color.RED);
        //g.drawRect(oliver.xPos, oliver.yPos, oliver.width, oliver.height);
        //g.setColor(Color.YELLOW);
//        g.drawRect(paper1.rec.x, paper1.rec.y, paper1.rec.width, paper1.rec.height);
//        g.drawRect(rock1.rec.x, rock1.rec.y, rock1.rec.width, rock1.rec.height);
//        g.drawRect(scissors1.rec.x, scissors1.rec.y, scissors1.rec.width, scissors1.rec.height);

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}
