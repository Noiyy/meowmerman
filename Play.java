import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Play here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Play extends Button {
    GreenfootSound selectSound = new GreenfootSound("select.wav");
    GreenfootSound hoverSound = new GreenfootSound("hover.wav");
    private boolean soundPlaying = false;
    
    public void act()
    {
        if (Greenfoot.mousePressed(this)) {
            this.setImage("button_play_action.png");
            if(!selectSound.isPlaying())
                selectSound.play();
            Greenfoot.setWorld(new Enviroment());
        }
        if (Greenfoot.mouseMoved(this)) {
            this.setImage("button_play_action.png");
            if(!hoverSound.isPlaying() && !soundPlaying) {
                hoverSound.play();
                soundPlaying = true;
            }
        }
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            this.setImage("button_play.png");
            soundPlaying = false;
        }
    }
}
