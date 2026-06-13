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
public class OldMan extends Person{
    private Sparrow sparrow;//association
    
    /**
     * Constructor to create OldMan object
     * 
     * @param p The Processing sketch
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param name The OldMan's name
     * @param sparrow The Sparrow object
     */
    public OldMan(PApplet p, int x, int y,String name,String imagePath, Sparrow sparrow){
        super(p,x,y,name,imagePath);//use parent class constructor
        this.sparrow=sparrow;
    }
}
