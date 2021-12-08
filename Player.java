import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor {
    private final String keyUp;
    private final String keyDown;
    private final String keyLeft;
    private final String keyRight;
    
    /*
     * SpriteFoot Animation by
     * 
     * @author Jordan Cohen
     * @version 0.2.0
     */
    private GreenfootImage[][] playerOne;
    private GreenfootImage[][] playerImage;

    private Direction direction;
    private int frame;

    private double xx, yy;

    private long lastFrame;
    private double framesPerSecond; // animation speed
    private int moveSpeed; // per second, not act
    private double secondsPerFrame;

    private int walkSpeed;
    private int walkAnimSpeed;

    private int runSpeed;
    private int runAnimSpeed;
    
    private boolean idle;
    
    public Player(String aKeyUp, String aKeyLeft, String aKeyDown, String aKeyRight) {
        keyUp = aKeyUp;
        keyLeft = aKeyLeft;
        keyDown = aKeyDown;
        keyRight = aKeyRight;
        
        // 4 directions, 9 frames per direction
        //basicMan = new GreenfootImage[4][9];
        direction = Direction.RIGHT;

        walkSpeed = 80;
        walkAnimSpeed = 30;

        framesPerSecond = walkAnimSpeed;
        moveSpeed = walkSpeed;

        secondsPerFrame = 1.0 / framesPerSecond;       
        lastFrame = System.nanoTime();

        frame = 0;
        idle = false;

        playerOne = importSprites ("p1_", Direction.size, 4);

        playerImage = playerOne;

        setImage (playerImage[direction.getDirection()][0]);
    }
    
    private GreenfootImage[][] importSprites (String baseString, int nDirs, int nFrames){
        GreenfootImage[][] temp = new GreenfootImage[nDirs][nFrames];
        for (int dir = 0; dir < nDirs; dir++){
            for (int frm = 0; frm < nFrames; frm++){
                String directionString;
                switch (dir) {
                    case 0: directionString = "Right";  break;
                    case 1: directionString = "Left";   break;
                    case 2: directionString = "Up";     break;
                    case 3: directionString = "Down";   break; 
                    default: directionString = "Error"; break;
                }

                String tempFileName = baseString + directionString + frm +".png";
                temp[dir][frm] = new GreenfootImage (tempFileName);
            }
        }
        return temp;
    }

    public void addedToWorld (World w){
        xx = getX();
        yy = getY();
        setLocation ((int)Math.round(xx), (int)Math.round(yy));
    }
    
    public void act() {
        // determine how much time has passed since the last act
        long current = System.nanoTime();
        // Find elapsed time - in milliseconds (ms)
        long elapsed = (current - lastFrame) / 1000000; 

        // Each act, movement and idle state are reset
        int moveX = 0, moveY = 0;

        // Keyboard iput for pressed keys
        String key = Greenfoot.getKey();

        if (key != null){
            if (key.equals("space")){
                //catbomb
            }
        }            

        /* For each key...
         * 
         *  RIGHT
         * 
         */
        Actor rightCollide = getOneObjectAtOffset(16, 0, Collisions.class);
        if (rightCollide != null) {
            if (Greenfoot.isKeyDown(keyRight)){
                moveX = 0; // set direction
                if (direction != direction.RIGHT){ // if I wasn't already moving this direction...
                    frame = 1; // start again at frame 1
                    direction = direction.RIGHT; // set direction to the newly specified direction
                }
            }
        } else {
            if (Greenfoot.isKeyDown(keyRight)){
                moveX = 1; // set direction
                if (direction != direction.RIGHT){ // if I wasn't already moving this direction...
                    frame = 1; // start again at frame 1
                    direction = direction.RIGHT; // set direction to the newly specified direction
                }
            }
        }
        
        /*
         *  LEFT
         */
        Actor leftCollide = getOneObjectAtOffset(-16, 0, Collisions.class);
        if (leftCollide != null) {
            if (Greenfoot.isKeyDown(keyLeft)){
                moveX = 0;
                if (direction != direction.LEFT){
                    frame = 1;
                    direction = direction.LEFT;
                }
            }
        } else {
            if (Greenfoot.isKeyDown(keyLeft)){
                moveX = -1;
                if (direction != direction.LEFT){
                    frame = 1;
                    direction = direction.LEFT;
                }
            }
        }
        if (moveX == 0){ // prevent diagonal movement - only check for Y if nothing is moving on X
            /*
             *  UP
             */
            Actor upCollide = getOneObjectAtOffset(0, -16, Collisions.class);
            if (upCollide != null) {
                if (Greenfoot.isKeyDown(keyUp)){
                    moveY = 0;
                    if(direction != direction.UP){
                        frame = 1;
                        direction = direction.UP;
                    }
                }
            } else {
                if (Greenfoot.isKeyDown(keyUp)){
                    moveY = -1;
                    if(direction != direction.UP){
                        frame = 1;
                        direction = direction.UP;
                    }
                }
            }
            /*
             *  DOWN
             */
            Actor downCollide = getOneObjectAtOffset(0, 16, Collisions.class);
            if (downCollide != null) {
            
            } else {
                if (Greenfoot.isKeyDown(keyDown)){
                    moveY = 1;
                    if (direction != direction.DOWN){
                        frame = 1; 
                        direction = direction.DOWN;
                    }
                }
            }
        }

        if (moveX == 0 && moveY == 0){ // if not moving, switch to idle
            idle = true;
            lastFrame = current;    // reset animation timer so it always starts fresh in next frame if
                                    // next frame is animated
            frame = 0;
            setImage (playerImage[direction.getDirection()][frame]);
        }        
        else{
            //System.out.println("System Nano: " + System.nanoTime());
            //System.out.println("Last: " + lastFrame);
            //System.out.println("Elapsed: " + elapsed);
            // Check if ready to show next frame, and if so, advance frame
            if (elapsed > secondsPerFrame * 1000 || idle == true){
                // note - the use of the idle variable here is to avoid restarting the animation
                // timer after idle. This way, the first frame after idle starts instantly
                lastFrame = current;
                frame++;
                idle = false;
            }

            if (frame > playerImage[direction.getDirection()].length - 1){
                frame = 1; // 0th frame is idle frame only, so count 1..last, not 0..last
            }
            // now that the calculations are done, set the correct image
            setImage (playerImage[direction.getDirection()][frame]);
        }
        // calculate exact new location. Decimal values will be rounded, but stored accurately, for
        // smooth animation over time
        xx += ((double)(moveX) * moveSpeed) * (elapsed / 1000.0);
        yy += ((double)(moveY) * moveSpeed) * (elapsed / 1000.0);

        // Normalize position - makes sure it can't go outside of world
        if (xx > getWorld().getBackground().getWidth() || xx < 0){
            xx = (double)getX();
        }
        if (yy > getWorld().getBackground().getHeight() || yy < 0){
            yy = (double)getY();
        }

        //setImage (playerImage[direction.getDirection()][frame]);
        setLocation ((int)Math.round(xx), (int)Math.round(yy));
        
    }
    
    // ENUM to keep direction related code clean. The array is set up so that 
    // index of main array corresponds with direction (I.e. img[0][2] is the 2nd frame in the RIGHT array)
    // which could also be written img[direction.getDirection()][2] to check the direction variable, of type
    // Direction, and use the corresponding int to access the correct image in the 2d array.
    public enum Direction {
        RIGHT(0), 
        LEFT(1), 
        UP(2), 
        DOWN(3);

        private final int dirCode;
        private Direction (int dirCode){
            this.dirCode = dirCode;
        }

        public int getDirection (){
            return this.dirCode;
        }

        public final static int size = Direction.values().length;
    }
    /*
     * End of SpriteFoot Animation
     */
    
    public void movementX(int num) {
        int moveX = 0;
        //
        
        moveX = num;
    }
    
    public void movementY(int num) {
        int moveY = 0;
        //
        
        moveY = num;
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
