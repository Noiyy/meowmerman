import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(672, 480, 1);
        createLogo();
        createButtons();
        createAdditional();
    }
    
    public void createLogo() {
        MenuLogo logo = new MenuLogo();
        this.addObject(logo, 336, 112);
    }
    
    public void createButtons() {
        Play playButton = new Play();
        this.addObject(playButton, 336, 310);
    }
    
    public void createAdditional() {

    }
}
