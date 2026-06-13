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
    private String name; //Person object's name
    
    /**
     * Constructor to create Person object
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param name The Person's name
     * @param imagePath The file path of the object's image
     */
    public Person(PApplet p, int x, int y,String name,String imagePath){
        super(p,x,y,imagePath);//use parent class constructor
        this.name=name;//set name
    }
    
    /**
     * Getter method that returns Person's name
     * 
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Method that displays object's name
     * 
     * @param p The Processing sketch
     */
    public void displayInfo(PApplet p){
        app.fill(0);//set color
        app.text("Name: "+name,x+55,y-30);
    }
}
