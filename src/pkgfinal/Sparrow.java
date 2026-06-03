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
    
    public Sparrow(PApplet app,int x, int y,String imagePath){
        super(app,x,y,imagePath,"Sparrow");
        tongueSplit = false;
    }
    
    public void fly(){
        x+=5;
    }
    
    public void splitTongue(){
        tongueSplit=true;
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
    
    @Override
    public void makeSound(){
        System.out.println("Chirp!");
    }

}
