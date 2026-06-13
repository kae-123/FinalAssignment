/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;

/**
 *
 * @author kaetg
 */
public class Creature extends GameObject{
    /**
     * Constructor to create creature object
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param imagePath The file path of the object's image
     */
    public Creature(PApplet p,int x, int y, String imagePath){
        super(p,x,y,imagePath); //use parent class constructor
    }
    
    @Override
    /**
     * Overridden method that the sound made by the object
     * 
     * @return The object's sound as a String
     */
    public String makeSound(){
        return "BOO!";
    }    
}
