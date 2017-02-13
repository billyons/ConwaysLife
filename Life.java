
/**
 *
 * @author eccmaina435-u7
 */
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class Life {

    private static World world;
    private static LifePanel panel;
    private static boolean[] growth;
    private static boolean[] stable;
    //edit these ones
    //find a list of cool behaviors at
    //http://en.wikipedia.org/wiki/Life-like_cellular_automaton
    private static final String growthRule = ("3");
    private static final String stableRule = ("1236");
    //for madness inducing wigglers that arent there when you look, 167/0235678
    // or inverted, 3/1246
    //patchwork sealing pattern 3/1236
    private static final int width = 400;
    private static final int height = 400;

    public static void main(String[] args){
        //initialize growth/death rules
        growth = new boolean[9];
        stable = new boolean[9];
        for(char i = '0'; i < '9'; i++){
            if(growthRule.indexOf(i) != -1){
                growth[Integer.parseInt(""+i)] = true;
            }
        }
        for(char i = '0'; i < '9'; i++){
            if(stableRule.indexOf(i) != -1){
                stable[Integer.parseInt(""+i)] = true;
            }
        }
        //create world
        Random rand = new Random();
        world = new World(width, height, growth, stable);
        //create display
        JFrame frame = new JFrame();
        frame.setTitle("Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new LifePanel(world);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        //create timer to advance the world
        Timer timer = new Timer(20, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                step();
            }
        });
        //populate the world with a random 10x10 square in the middle
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(rand.nextInt(2)==1){
                    world.setAlive(i+width/2-5,j+height/2-5);
                }
            }
        }
        /*for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(rand.nextInt(2)==1){
                    world.setAlive(i+130,j+130);
                }
            }
        }*/
        //start the timer
        timer.start();
    }

    public static void step(){
        world.print(panel);
        world.advance();
    }

}
