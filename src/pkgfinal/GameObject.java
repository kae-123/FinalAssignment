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
    protected int x,y;
    protected PApplet app;
    protected PImage image;
    
    public GameObject(PApplet p,int x,int y,String imagePath){
        this.app=p;
        this.x=x;
        this.y=y;
        this.image = app.loadImage(imagePath);
    }
    
    public void display(){
        app.image(image, x, y);
    }
    
    public void move(int dx,int dy){
        x+=dx;
        y+=dy;
    }
    
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void setNewImage(String imagePath){
        this.image = app.loadImage(imagePath);
    }
    
    public boolean isClicked(float mx, float my) {
        return (mx >= x && mx <= x + image.width && my >= y && my <= y + image.height);
    }
}
