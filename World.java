
/**
 *
 * @author eccmaina435-u7
 */
public class World {

    private byte[][] grid;
    private boolean[] growthRule;
    private boolean[] stableRule;
    private int width;
    private int height;

    public World(int height, int width, boolean[] growth, boolean[] stable){
        //create grid
        grid = new byte[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = 0;
            }
        }
        //implement rules
        growthRule = growth;
        stableRule = stable;
        this.width=width;
        this.height=height;

    }

    public void advance(){
        //advance the world by 1 generation of cells
        byte[][] newGrid = new byte[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                newGrid[i][j] = getNext(i, j);
            }
        }

        grid = newGrid;
    }

    public byte getNext(int i, int j){
        //internal method for determining the next state of a given cell
        byte next = 0;

        int adjacent = getAdjacent(i,j);
        //Growth test
        if(grid[i][j]==0){
            if(growthRule[adjacent]){
                next = 1;
            }
        } else { //stable test
            if(stableRule[adjacent]){
                next = 1;
            }
        }

        return next;
    }

    public int getAdjacent(int i, int j){
        //internal method for determining the number of adjacent living cells
        int adjacent = 0;
        //probably not optimal by a long shot
        for(int k = -1; k < 2; k++){
            for(int l = -1; l < 2; l++){
                if(!(k==0 && l ==0)){
                    if(i+k >= 0 && i+k <= grid.length-1 && j+l >= 0 && j+l <= grid[0].length-1){
                        adjacent+=grid[i+k][j+l];
                    }
                 }
            }
        }

        return adjacent;
    }

    //it's nice to have some control
    public void setAlive(int i, int j){
        grid[i][j] = 1;
    }
    //and be able to smite, too >:D
    public void setDead(int i, int j){
        grid[i][j] = 0;
    }
    //before I wrote the graphics
    public void print(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                switch(grid[i][j]){
                    case 0: System.out.print(" "); break;
                    default: System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void print(LifePanel panel){
        panel.repaint();
    }
    //accessors

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public byte[][] getGrid(){
        return grid;
    }

}
