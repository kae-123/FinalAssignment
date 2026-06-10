/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author 345341119
 */
public class Person extends GameObject{
    private String name;
    
    public Person(PApplet p, int x, int y,String name,String imagePath){
        super(p,x,y,imagePath);
        this.name=name;
    }
    
    public void move(int dx, int dy){
      x+=dx;
      y+=dy;
    }
    
    public String getName(){
        return name;
    }
  
    public void draw(){
        app.image(image, x, y);
    }
    
    public boolean isCollidingWith(Person other){
        return true;
    }
    
    public void displayInfo(PApplet p){
        app.fill(0);
        app.text("Name: "+name,x,y-50);
    }
}
