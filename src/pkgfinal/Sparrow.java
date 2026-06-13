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
    private boolean tongueSplit;
    private String name;
    private static int totalStarchCollected = 0;
    private int health;
    
    public Sparrow(PApplet p,int x, int y,String imagePath){
        super(p,x,y,imagePath);
        this.name = "the Sparrow";
        tongueSplit = false;
        health =100;
    }
    
    public Sparrow(PApplet p,int x, int y,String imagePath,String name){
        super(p,x,y,imagePath);
        this.name = name;
        tongueSplit = false;
        health =100;
    }
    
    public String getName(){
        return name;
    }
    
    public void fly(){
        x+=5;
        y-=10;
    }
    
    public static void collectStarch(){
        totalStarchCollected++;
    }
    
    public static int getTotalStarchCollected(){
        return totalStarchCollected;
    }
    
    public void splitTongue(){
        tongueSplit=true;
    }
    
    public boolean isCollidingWith(OldMan other){
        boolean isLeftOfOtherRight = x<other.x+other.image.width;
        boolean isRightOfOtherLeft = x+image.width>other.x;
        boolean isAboveOtherButton = y<other.y+other.image.height;
        boolean isBelowOtherTop= y+image.height>other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft&&
                isAboveOtherButton && isBelowOtherTop;
    }
    
    public boolean isCollidingWith(OldWoman other){
        boolean isLeftOfOtherRight = x<other.x+other.image.width;
        boolean isRightOfOtherLeft = x+image.width>other.x;
        boolean isAboveOtherButton = y<other.y+other.image.height;
        boolean isBelowOtherTop= y+image.height>other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft&&
                isAboveOtherButton && isBelowOtherTop;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void loseHealth(int amt){
        health-=amt;
    }
}
