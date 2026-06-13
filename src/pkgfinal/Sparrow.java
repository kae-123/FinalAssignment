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
public class Sparrow extends GameObject{
    private boolean tongueSplit;//boolean used to determine if tongue split
    private String name;//Object's name
    private int health;//health
    private static int totalStarchCollected = 0;//static variable set to 0
    
    /**
     * Constructor that creates the sparrow object
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param imagePath The file path of the object's image
     */
    public Sparrow(PApplet p,int x, int y,String imagePath){
        super(p,x,y,imagePath);//use parent class constructor
        this.name = "the Sparrow";//set default name
        tongueSplit = false;//set tongueSplit to false
        health =100; //set health to 100
    }
    
    /**
     * Overloaded constructor to create sparrow object with 
     * user-inputted name
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param imagePath The file path of the object's image
     * @param name The sparrow's name
     */
    public Sparrow(PApplet p,int x, int y,String imagePath,String name){
        super(p,x,y,imagePath);//use parent class constructor
        this.name = name;//set name
        tongueSplit = false;//set tongueSplit to false
        health =100;//set health to 100
    }
    
    /**
     * Getter method to get sparrow's name
     * 
     * @return Sparrow's name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Method that moves object 5 horizontally and 10
     * vertically
     */
    public void fly(){
        x+=5;
        y-=10;
    }
    
    /**
     * Method to increment totalStarchCollected
     */
    public static void collectStarch(){
        totalStarchCollected++;
    }
    
    /**
     * Getter method to return totalStarchCollected
     * @return totalStarchCollected
     */
    public static int getTotalStarchCollected(){
        return totalStarchCollected;
    }
    
    /**
     * Method to set tongueSplit to true
     */
    public void splitTongue(){
        tongueSplit=true;
    }
    
    /**
     * Determines whether the sparrow is colliding with the OldMan obj.
     * Uses rect collision detection by checking overlap
     * 
     * @param other The OldMan obj
     * @return true if they are colliding
     */
    public boolean isCollidingWith(OldMan other){
        boolean isLeftOfOtherRight = x<other.x+other.image.width;
        boolean isRightOfOtherLeft = x+image.width>other.x;
        boolean isAboveOtherButton = y<other.y+other.image.height;
        boolean isBelowOtherTop= y+image.height>other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft&&
                isAboveOtherButton && isBelowOtherTop;
    }
    
    /**
     * Determines whether the sparrow is colliding with the OldWoman obj.
     * Uses rect collision detection by checking overlap
     * 
     * @param other The OldWoman object
     * @return true if they are colliding
     */
    public boolean isCollidingWith(OldWoman other){
        boolean isLeftOfOtherRight = x<other.x+other.image.width;
        boolean isRightOfOtherLeft = x+image.width>other.x;
        boolean isAboveOtherButton = y<other.y+other.image.height;
        boolean isBelowOtherTop= y+image.height>other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft&&
                isAboveOtherButton && isBelowOtherTop;
    }
    
    /**
     * Getter method that returns the health
     * 
     * @return health
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * Method that subtract specified amt from health
     * 
     * @param amt The amount that health decreases by
     */
    public void loseHealth(int amt){
        health-=amt;
    }
    
    @Override
    /**
     * Overridden method that the sound made by the object
     * 
     * @return The object's sound as a String
     */
    public String makeSound(){
        return "Chirp!";
    }
}
