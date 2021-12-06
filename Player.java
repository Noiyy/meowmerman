import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor {
    private int speed = 4;
    
    private final String keyUp;
    private final String keyDown;
    private final String keyLeft;
    private final String keyRight;
    
    public Player(String aKeyUp, String aKeyLeft, String aKeyDown, String aKeyRight) {
        keyUp = aKeyUp;
        keyLeft = aKeyLeft;
        keyDown = aKeyDown;
        keyRight = aKeyRight;
    }
    
    public void act() {
        if (Greenfoot.isKeyDown(keyUp)) {
            //this.setImage("images/p1_up0.png");
            // rotate to north (up)
            if(getRotation() != 90*3) {
                setRotation(90*3);
            }
            //
            movePlayer(speed);
        } else if (Greenfoot.isKeyDown(keyDown)) {
            //this.setImage("images/p1_down0.png");
            // rotate to south (down)
            if(getRotation() != 90*1) {
                setRotation(90*1);
            }
            //
            movePlayer(speed);
        } else if (Greenfoot.isKeyDown(keyLeft)) {
            //this.setImage("images/p1_left0.png");
            // rotate to west (left)
            if(getRotation() != 90*2) {
                setRotation(90*2);
            }
            //
            movePlayer(speed);
        } else if (Greenfoot.isKeyDown(keyRight)) {
            //this.setImage("images/p1_right0.png");
            // rotate to east (right)
            if(getRotation() != 90*0) {
                setRotation(90*0);
            }
            //
            movePlayer(speed);
        }
    }
    
    public boolean canMove() {
        return !isTouching(Collisions.class);
        //return true;
    }
    
    public void movePlayer(int speed) {
        Actor wall = getOneIntersectingObject(Collisions.class);
        
        if(canMove()) {
            move(speed);
        // move only down, right, left if player is touching wall above him
        } else if (!canMove() && wall.getY() < this.getY() && wall.getY() == 16 ) {
            if ( this.getRotation() != 90*3 )
                move(speed);
                
        // move only up, right, left if player is touching wall beneath him
        } else if (!canMove() && wall.getY() > this.getY() && wall.getY() == 464 ) {  
            if ( this.getRotation() != 90*1 )
                move(speed);
                
        // move only right, up, down if player is touching wall left to him
        } else if (!canMove() && wall.getX() < this.getX() && wall.getX() == 16 ) {
            if ( this.getRotation() != 90*2 )
                move(speed);
                
        // move only left, up, down if player is touching wall right to him
        } else if (!canMove() && wall.getX() > this.getX() && wall.getX() == 656 ) {
            if ( this.getRotation() != 90*0 ) 
                move(speed); 
        }
    }
    
}
