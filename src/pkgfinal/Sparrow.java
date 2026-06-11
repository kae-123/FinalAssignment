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
public class Sparrow extends Animal{
    private boolean tongueSplit;
    
    public Sparrow(PApplet p,int x, int y,String imagePath){
        super(p,x,y,imagePath,"Sparrow");
        tongueSplit = false;
    }
    
    public void fly(){
        x+=5;
        y-=10;
    }
    
    public void splitTongue(){
        tongueSplit=true;
    }
    
    public boolean isTongueSplit(){
        return tongueSplit;
    }
    
    public void move(int dx, int dy){
        this.x+=dx;
        this.y+=dy;
    }
    
    public void setX(int x){
        this.x =x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public boolean isClicked(int mouseX, int mouseY) {
        /*calculates distance from mouse click at mouseX and mouseY to center 
        * of image since (x,y) of image is postioned at the top left corner  
        * we use x+(image.pixelWidth/2), y+(image.pixelHeight/2)) to get center*/
        int centerX = x+(image.pixelWidth/2);
        int centerY = y+(image.pixelHeight/2);        
        float d = PApplet.dist(mouseX, mouseY, centerX ,centerY );

        //gives us the dimensions of the image 32px by 32px
        System.out.println("image height"+image.pixelHeight);
        System.out.println("image width"+image.pixelWidth);

        // returns true if  mouse clicked is within 16px from the center of image
        // we use 16px because the image is 32px by 32px
        return d < 16; 
    }
    
    public boolean isCollidingWith(OldMan other){
        boolean isLeftOfOtherRight = x<other.x+other.image.width;
        boolean isRightOfOtherLeft = x+image.width>other.x;
        boolean isAboveOtherButton = y<other.y+other.image.height;
        boolean isBelowOtherTop= y+image.height>other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft&&
                isAboveOtherButton && isBelowOtherTop;
    }
    
    public boolean touchingStarch(int sx,int sy, int sw, int sh){
        return x < sx + sw &&
           x + image.width > sx &&
           y < sy + sh &&
           y + image.height > sy;
    }
    
    
    
    @Override
    public void makeSound(){
        System.out.println("Chirp!");
    }

}
