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
public class OldWoman extends Person{
    /**
     * Constructor to create OldWoman object
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param name The OldWoman's name
     * @param imagePath The file path of the object's image
     */
    public OldWoman(PApplet p, int x, int y,String name,String imagePath){
        super(p,x,y,name,imagePath);//use parent class constructor
    }
}