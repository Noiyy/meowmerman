import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacle extends Actor {
    
    public void act() {
        if (isTouching(Collisions.class)) {
            getWorld().removeObject(this);
        }
    }
    
    private boolean nearPlayer() {
        return this.getObjectsInRange(32, Player.class).isEmpty();
    }
    
    public void addedToWorld(World w) {
        if (!nearPlayer()) {
            getWorld().removeObject(this);
        }
    }
}
