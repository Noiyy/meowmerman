import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Helper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Helper extends Actor {
    GreenfootImage helperImg = new GreenfootImage(32, 32);
    
    public Helper() {
        helperImg.setColor(Color.BLACK);
        helperImg.fill();
        helperImg.setTransparency(0);
        
        this.setImage(helperImg);
    }
    
    public void act() {
        if (isTouching(null)) {
            System.out.println("meow");
            getWorld().removeObject(this);
        }
    }    
}
