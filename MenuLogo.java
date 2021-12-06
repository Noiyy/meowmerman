import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuLogo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuLogo extends Text {
    GreenfootSound meowSound = new GreenfootSound("meow.mp3");
    
    public void act()
    {
        if (Greenfoot.mousePressed(this)) {
            meowSound.play();
        }
    }
}
