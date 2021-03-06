import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enviroment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enviroment extends World {
    private int wallsX[] = {2, 4, 6, 8, 10, 12, 14, 16, 18};
    private int wallsY[] = {2, 4, 6, 8, 10, 12};
    private int helpersX[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    private int helpersY[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    //
    private int cellX = 16, cellY = 16;
    
    public Enviroment()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(672, 480, 1);             // 21, 15, 32
        createBorders(1, 1);
        createWalls(wallsX, wallsY);
        createPlayer(32*1+16, 32*11+16);
        createHelpers(helpersX, helpersY);
    }
    
    public void createBorders(int x, int y) {
        // render them for X axis
        // cWid = cellWidth = 20
        
        this.addObject(new Collisions(), cellX*x , cellY*y);
        x = 32; y = 32;
        
        for (int cWid = 1; cWid <= 21*2; cWid++) {
            // up
            if(cWid < 21) {
                this.addObject(new Collisions(), cellX+=x , cellY);
            // down
            } else if (cWid == 21) {
                cellY=480-16;
                this.addObject(new Collisions(), cellX , cellY);
            } else if (cWid > 21 && cWid < 21*2)
                this.addObject(new Collisions(), cellX-=x , cellY);
        }
        
        // render them for Y axis
        // cHei = cellHeight = 15-1
        cellY = 16;
        for (int cHei = 1; cHei <= 14*2; cHei++) {
            // left
            if(cHei < 14) {
                this.addObject(new Collisions(), cellX , cellY+=y);
            // right
            } else if (cHei == 14) {
                cellX=672-16;
                this.addObject(new Collisions(), cellX , cellY);
            } else if (cHei > 14 && cHei < (14*2)-1)
                this.addObject(new Collisions(), cellX , cellY-=y);
        }
    }
    
    public void createPlayer(int x, int y) {
        //
        Player player = new Player("up", "left", "down", "right");
        this.addObject(player, x, y);
    }
    
    public void createWalls(int x[], int y[]) {
        for (int o = 0; o < wallsX.length; o++ ) {
            for (int i = 0; i < wallsY.length; i++) {
                this.addObject(new Collisions(), x[o]*32+16, y[i]*32+16);
            }
        }
    }
    
    public void createHelpers(int x[], int y[]) {
        for (int o = 0; o < helpersX.length; o++) {
            for (int i = 0; i < helpersY.length; i++) {
                this.addObject(new Helper(), x[o]*32+16, y[i]*32+16);
            }
        }
    }
}
