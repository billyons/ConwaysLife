
/**
 *
 * @author eccmaina435-u7
 */
import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private World world;
    private byte[][] grid;
    private final int BLOCKSIZE = 2;

    public LifePanel(World w){
        world = w;
        //set the size based on the world grid and the pixels per square
        setPreferredSize(new Dimension(world.getWidth()*BLOCKSIZE,
                world.getHeight()*BLOCKSIZE));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //clear screen
        grid = world.getGrid();
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        //paint all living blocks
        g.setColor(Color.black);
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1){
                    g.fillRect(BLOCKSIZE*i, BLOCKSIZE*j, BLOCKSIZE, BLOCKSIZE);
                }
            }
        }
    }

}
