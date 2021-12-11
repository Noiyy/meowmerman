import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Helper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Helper extends Actor {
    GreenfootImage helperImg = new GreenfootImage(32, 32);
    private int obstaclePerHelper = 1;
    
    public Helper() {
        helperImg.setColor(Color.BLACK);
        helperImg.fill();
        helperImg.setTransparency(50);
        
        this.setImage(helperImg);
    }
    
    public int getRandomNumber(int start,int end)
    {
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }
    
    public void act() {
        if (isTouching(null)) {
            getWorld().removeObject(this);  // remove helper when it's touching something
        } else if (obstaclePerHelper <= 1) {
            obstaclePerHelper++; 
            if(Greenfoot.getRandomNumber(100)<50) { // 50% chance for adding obstacle
                createObstacle();
            }
        }
    }
    
    public void createObstacle() {
        getWorld().addObject(new Obstacle(), this.getX(), this.getY() );
    }
}
