/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author kaetg
 */
public class GameObject {
    protected int x,y; //x and y coordiante of object
    protected PApplet app;//Processing sketch
    protected PImage image;//Image used to represent the object
    
    /**
     * Constructor for every object in the game 
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param imagePath The file path of the object's image
     */
    public GameObject(PApplet p,int x,int y,String imagePath){
        this.app=p;
        this.x=x;
        this.y=y;
        this.image = app.loadImage(imagePath);
    }
    
    /**
     * Displays the object's image at its current position
     */
    public void display(){
        app.image(image, x, y);
    }
    
    /**
     * Moves the object by the specified amount
     * 
     * @param dx The amt to move horizontally
     * @param dy The amt to move vertically
     */
    public void move(int dx,int dy){
        x+=dx;
        y+=dy;
    }
    
    /**
     * Sets the object's position to the specified coordinates
     * 
     * @param x The new x-coordinate
     * @param y The new y-coordinate
     */
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    /**
     * Changes the image used for the object
     * 
     * @param imagePath The file path of new image
     */
    public void setNewImage(String imagePath){
        this.image = app.loadImage(imagePath);
    }
    
    /**
     * Determines whether a click occurred
     * 
     * @param mx The x-coordinate of mouse
     * @param my The y-coordinate of mouse
     * @return true if click is inside the image
     */
    public boolean isClicked(float mx, float my) {
        return (mx >= x && mx <= x + image.width && my >= y && my <= y + image.height);
    }
    
    /**
     * Returns the sound made by the object
     * 
     * @return The object's sound as a String
     */
    public String makeSound(){
        return "Sound";
    }
}
